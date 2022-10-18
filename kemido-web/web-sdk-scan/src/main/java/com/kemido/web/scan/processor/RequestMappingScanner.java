package com.kemido.web.scan.processor;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.web.core.definition.RequestMappingScanManager;
import com.kemido.web.core.support.WebPropertyFinder;
import com.kemido.web.core.domain.RequestMapping;
import com.kemido.web.scan.properties.ScanProperties;
import cn.hutool.core.util.HashUtil;
import cn.hutool.crypto.SecureUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: RequestMapping扫描器 </p>
 */
public class RequestMappingScanner implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(RequestMappingScanner.class);

    private final ScanProperties restProperties;
    private final RequestMappingScanManager requestMappingScanManager;

    public RequestMappingScanner(ScanProperties restProperties, RequestMappingScanManager requestMappingScanManager) {
        this.restProperties = restProperties;
        this.requestMappingScanManager = requestMappingScanManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        ApplicationContext applicationContext = event.getApplicationContext();

        log.debug("[Kemido] |- [1] Application is READY, start to scan request mapping!");
        onApplicationEvent(applicationContext);
    }

    public void onApplicationEvent(ApplicationContext applicationContext) {
        // 1、获取服务ID：该服务ID对于微服务是必需的。
        String serviceId = WebPropertyFinder.getApplicationName(applicationContext.getEnvironment());

        // 2、只针对有EnableResourceServer注解的微服务进行扫描。如果变为单体架构目前不会用到EnableResourceServer所以增加的了一个Architecture判断
        if (!requestMappingScanManager.isPerformScan()) {
            // 只扫描资源服务器
            log.warn("[Kemido] |- Can not found scan annotation in Service [{}], Skip!", serviceId);
            return;
        }

        // 3、获取所有接口映射
        Map<String, RequestMappingHandlerMapping> mappings = applicationContext.getBeansOfType(RequestMappingHandlerMapping.class);

        // 4、 获取url与类和方法的对应信息
        List<RequestMapping> resources = new ArrayList<>();
        for (RequestMappingHandlerMapping mapping : mappings.values()) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
            if (MapUtils.isNotEmpty(handlerMethods)) {
                for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                    RequestMappingInfo requestMappingInfo = entry.getKey();
                    HandlerMethod handlerMethod = entry.getValue();

                    // 4.1、如果是被排除的requestMapping，那么就进行不扫描
                    if (isExcludedRequestMapping(handlerMethod)) {
                        continue;
                    }

                    // 4.2、拼装扫描信息
                    RequestMapping requestMapping = createRequestMapping(serviceId, requestMappingInfo, handlerMethod);
                    if (ObjectUtils.isEmpty(requestMapping)) {
                        continue;
                    }

                    resources.add(requestMapping);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(resources)) {
            log.debug("[Kemido] |- [2] Request mapping scan found [{}] resources in service [{}], go to next stage!", serviceId, resources.size());
            requestMappingScanManager.process(resources);
        } else {
            log.debug("[Kemido] |- [2] Request mapping scan can not find any resources in service [{}]!", serviceId);
        }

        log.info("[Kemido] |- Request Mapping Scan for Service: [{}] FINISHED!", serviceId);
    }

    /**
     * 检测RequestMapping是否需要被排除
     *
     * @param handlerMethod HandlerMethod
     * @return boolean
     */
    private boolean isExcludedRequestMapping(HandlerMethod handlerMethod) {
        if (!isSpringAnnotationMatched(handlerMethod)) {
            return true;
        }

        return !isSwaggerAnnotationMatched(handlerMethod);
    }

    /**
     * 如果开启isJustScanRestController，那么就只扫描RestController
     *
     * @param handlerMethod HandlerMethod
     * @return boolean
     */
    private boolean isSpringAnnotationMatched(HandlerMethod handlerMethod) {
        if (restProperties.isJustScanRestController()) {
            return handlerMethod.getMethod().getDeclaringClass().getAnnotation(RestController.class) != null;
        }

        return true;
    }

    /**
     * 有ApiIgnore注解的方法不扫描, 没有ApiOperation注解不扫描
     *
     * @param handlerMethod HandlerMethod
     * @return boolean
     */
    private boolean isSwaggerAnnotationMatched(HandlerMethod handlerMethod) {
        if (handlerMethod.getMethodAnnotation(Hidden.class) != null) {
            return false;
        }

        Operation operation = handlerMethod.getMethodAnnotation(Operation.class);
        return ObjectUtils.isNotEmpty(operation) && !operation.hidden();
    }

    /**
     * 如果当前class的groupId在GroupId列表中，那么就进行扫描，否则就排除
     *
     * @param className 当前扫描的controller类名
     * @return Boolean
     */
    private boolean isLegalGroup(String className) {
        if (StringUtils.isNotEmpty(className)) {
            List<String> groupIds = restProperties.getScanGroupIds();
            List<String> result = groupIds.stream().filter(groupId -> StringUtils.contains(className, groupId)).collect(Collectors.toList());
            return !CollectionUtils.sizeIsEmpty(result);
        } else {
            return false;
        }
    }

    private RequestMapping createRequestMapping(String serviceId, RequestMappingInfo info, HandlerMethod method) {
        // 4.2.1、获取类名
        // method.getMethod().getDeclaringClass().getName() 取到的是注解实际所在类的名字，比如注解在父类叫BaseController，那么拿到的就是BaseController
        // method.getBeanType().getName() 取到的是注解实际Bean的名字，比如注解在在父类叫BaseController，而实际类是SysUserController，那么拿到的就是SysUserController
        String className = method.getBeanType().getName();

        // 4.2.2、检测该类是否在GroupIds列表中
        if (!isLegalGroup(className)) {
            return null;
        }

        // 5.2.3、获取不包含包路径的类名
        String classSimpleName = method.getBeanType().getSimpleName();

        // 4.2.4、获取RequestMapping注解对应的方法名
        String methodName = method.getMethod().getName();

        // 5.2.5、获取注解对应的请求类型
        RequestMethodsRequestCondition requestMethodsRequestCondition = info.getMethodsCondition();
        String requestMethods = StringUtils.join(requestMethodsRequestCondition.getMethods(), SymbolConstants.COMMA);

        // 5.2.6、获取主机对应的请求路径
        PathPatternsRequestCondition pathPatternsCondition = info.getPathPatternsCondition();
        Set<String> patternValues = pathPatternsCondition.getPatternValues();
        if (CollectionUtils.isEmpty(patternValues)) {
            return null;
        }

        String urls = StringUtils.join(patternValues, SymbolConstants.COMMA);
        // 对于单体架构路径一般都是menu，还是手动设置吧。
//        if (!isDistributedArchitecture()) {
//            if (StringUtils.contains(urls, "index")) {
//                return null;
//            }
//        }

        // 5.2.7、微服务范围更加粗放， 单体架构应用通过classSimpleName进行细化
//        String identifyingCode = isDistributedArchitecture() ? serviceId : classSimpleName;

        // 5.2.8、根据serviceId, requestMethods, urls生成的MD5值，作为自定义主键
        String flag = serviceId + SymbolConstants.DASH + requestMethods + SymbolConstants.DASH + urls;
        String id = SecureUtil.md5(flag);
        int code = HashUtil.fnvHash(flag);

        // 5.2.9、组装对象
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMetadataId(id);
        requestMapping.setMetadataCode(BaseConstants.AUTHORITY_PREFIX + code);
        // 微服务需要明确ServiceId，同时也知道ParentId，Hammer有办法，但是太繁琐，还是生成数据后，配置一把好点。
//        if (isDistributedArchitecture()) {
//            requestMapping.setServiceId(identifyingCode);
//            requestMapping.setParentId(identifyingCode);
//        }
        requestMapping.setServiceId(serviceId);
        requestMapping.setParentId(serviceId);
        Operation apiOperation = method.getMethodAnnotation(Operation.class);
        if (ObjectUtils.isNotEmpty(apiOperation)) {
            requestMapping.setMetadataName(apiOperation.summary());
//            requestMapping.setDescription(apiOperation.description());
        }
        requestMapping.setRequestMethod(requestMethods);
        requestMapping.setUrl(urls);
        requestMapping.setClassName(className);
        requestMapping.setMethodName(methodName);
        return requestMapping;
    }
}
