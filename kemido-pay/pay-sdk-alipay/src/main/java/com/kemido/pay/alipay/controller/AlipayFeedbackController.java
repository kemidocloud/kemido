package com.kemido.pay.alipay.controller;

import com.kemido.pay.alipay.definition.AlipayPaymentTemplate;
import cn.hutool.extra.servlet.ServletUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>Description: 支付宝异步通知、返回接口 </p>
 */
@RestController
@RequestMapping("/open/pay/alipay")
@Tags({@Tag(name = "支付接口"), @Tag(name = "支付宝支付接口"), @Tag(name = "支付宝异步通知返回接口")})
public class AlipayFeedbackController {

    @Autowired
    private AlipayPaymentTemplate alipayPaymentTemplate;

    @Operation(summary = "支付宝通知", description = "获取支付宝POST过来反馈信息",
            responses = {@ApiResponse(description = "单位树", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "httpServletRequest", description = "标准请求对象实体HttpServletRequest", schema = @Schema(implementation = HttpServletRequest.class)),
    })
    @PostMapping("/notify")
    public void paymentNotify(HttpServletRequest httpServletRequest) {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = ServletUtil.getParamMap(httpServletRequest);
        alipayPaymentTemplate.paymentNotify(params);
    }

    @Operation(summary = "支付宝返回", description = "获取支付宝POST过来反馈信息",
            responses = {@ApiResponse(description = "单位树", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "httpServletRequest", description = "标准请求对象实体HttpServletRequest", schema = @Schema(implementation = HttpServletRequest.class)),
    })
    @GetMapping("/return")
    public void paymentReturn(HttpServletRequest request) {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = ServletUtil.getParamMap(request);
        alipayPaymentTemplate.paymentReturn(params);
    }

}
