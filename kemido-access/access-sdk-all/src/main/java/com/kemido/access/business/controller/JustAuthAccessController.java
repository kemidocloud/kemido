package com.kemido.access.business.controller;

import com.kemido.access.business.event.AutomaticSignInEvent;
import com.kemido.access.justauth.processor.JustAuthProcessor;
import com.kemido.assistant.core.domain.Result;
import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.zhyd.oauth.model.AuthCallback;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 社交登录第三方系统返回的Redirect Url </p>
 */
@RestController
@Tag(name = "社交登录Redirect Url")
public class JustAuthAccessController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JustAuthProcessor justAuthProcessor;

    @Operation(summary = "社交登录redirect url地址", description = "社交登录标准模式的redirect url地址，获取第三方登录返回的code")
    @Parameters({
            @Parameter(name = "source", required = true, description = "社交登录的类型，具体指定是哪一个第三方系统", in = ParameterIn.PATH),
    })
    @RequestMapping("/open/identity/social/{source}")
    public void callback(@PathVariable("source") String source, AuthCallback callback) {
        if (StringUtils.isNotBlank(source) && BeanUtil.isNotEmpty(callback)) {
            Map<String, Object> params = ImmutableMap.of("source", source, "callback", callback);
            applicationContext.publishEvent(new AutomaticSignInEvent(params));
        }
    }

    @Operation(summary = "获取社交登录列表", description = "根据后台已配置社交登录信息，返回可用的社交登录控制列表")
    @GetMapping("/open/identity/sources")
    public Result<Map<String, String>> list() {
        Map<String, String> list = justAuthProcessor.getAuthorizeUrls();
        if (MapUtils.isNotEmpty(list)) {
            return Result.success("获取社交登录列表成功", list);
        } else {
            return Result.success("社交登录没有配置", new HashMap<>());
        }
    }
}
