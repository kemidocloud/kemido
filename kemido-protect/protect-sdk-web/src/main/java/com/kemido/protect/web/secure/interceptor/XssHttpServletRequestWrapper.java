package com.kemido.protect.web.secure.interceptor;

import com.kemido.assistant.core.utils.XssUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Description: Xss 请求参数包装器  </p>
 * <p>
 * Content-Type	传参方式	接收方式
 * application/x-www-form-urlencoded	表单key-value	HttpServletRequest Parameters 获取
 * multipart/form-data	表单key-value	HttpServletRequest Parameters 获取
 * application/json	json格式文本	HttpServletRequest IO流获取
 * <p>
 * 本过滤器主要针对表单提交的参数过滤
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger log = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 使用AntiSamy清洗数据
     *
     * @param value 需要清洗的数据
     * @return 清洗后的数据
     */
    private String cleaning(String value) {
        return XssUtils.cleaning(value);
    }

    private String[] cleaning(String[] parameters) {
        List<String> cleanParameters = Arrays.stream(parameters).map(XssUtils::cleaning).collect(Collectors.toList());
        String[] results = new String[cleanParameters.size()];
        return cleanParameters.toArray(results);
    }

    /**
     * 过滤请求头
     *
     * @param name 参数名
     * @return 参数值
     */
    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        // 如果Header为空，则直接返回，否则进行清洗
        return StringUtils.isBlank(header) ? header : cleaning(header);
    }

    @Override
    public String getParameter(String name) {
        String header = super.getHeader(name);
        // 如果Header为空，则直接返回，否则进行清洗
        return StringUtils.isBlank(header) ? header : cleaning(header);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (ArrayUtils.isNotEmpty(parameterValues)) {
            return cleaning(parameterValues);
        }
        return super.getParameterValues(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        return parameterMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> cleaning(entry.getValue())));
    }
}
