package com.kemido.access.business.controller;

import com.kemido.access.business.processor.AccessHandlerStrategyFactory;
import com.kemido.access.core.definition.AccessResponse;
import com.kemido.assistant.core.domain.Result;
import com.kemido.assistant.core.enums.AccountType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 手机验证码登录 </p>
 */
@RestController
@Tag(name = "手机验证码登录接口")
public class PhoneNumberAccessController {

    @Autowired
    private AccessHandlerStrategyFactory accessHandlerStrategyFactory;

    @Operation(summary = "手机验证码发送地址", description = "接收手机号码，发送验证码，并缓存至Redis")
    @Parameters({
            @Parameter(name = "mobile", required = true, description = "手机号码"),
    })
    @PostMapping("/open/identity/verification-code")
    public Result<String> sendCode(@RequestParam("mobile") String mobile) {
        AccessResponse response = accessHandlerStrategyFactory.preProcess(AccountType.SMS, mobile);
        if (ObjectUtils.isNotEmpty(response)) {
            if (response.getSuccess()) {
                return Result.success("短信发送成功！");
            } else {
                return Result.failure("短信发送失败！");
            }
        }
        return Result.failure("手机号码接收失败！");
    }
}
