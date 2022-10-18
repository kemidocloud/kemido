package com.kemido.oauth2.server.authorization.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.oauth2.core.definition.domain.Authority;
import com.kemido.oauth2.core.definition.strategy.StrategyAuthorityDetailsService;
import com.kemido.oauth2.server.authorization.entity.OAuth2Authority;
import com.kemido.rest.core.controller.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: OAuth2 权限接口 </p>
 */
@RestController
@RequestMapping("/authorize/authority")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 权限读取接口")
})
public class OAuth2AuthorityController implements Controller {

    private final StrategyAuthorityDetailsService strategyAuthorityDetailsService;

    @Autowired
    public OAuth2AuthorityController(StrategyAuthorityDetailsService strategyAuthorityDetailsService) {
        this.strategyAuthorityDetailsService = strategyAuthorityDetailsService;
    }

    @Operation(summary = "查询所有权限数据", description = "查询所有权限数据用于给Scope分配权限",
            responses = {@ApiResponse(description = "权限列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OAuth2Authority.class)))})
    @GetMapping("/condition")
    public Result<List<OAuth2Authority>> findAll() {
        List<Authority> authorities = strategyAuthorityDetailsService.findAll();
        if (CollectionUtils.isNotEmpty(authorities)) {
            List<OAuth2Authority> result = toEntities(authorities);
            return result(result);
        } else {
            return Result.empty("未查询到数据");
        }
    }

    private List<OAuth2Authority> toEntities(List<Authority> authorities) {
        return authorities.stream().map(this::toEntity).collect(Collectors.toList());
    }

    private OAuth2Authority toEntity(Authority object) {
        OAuth2Authority authority = new OAuth2Authority();
        authority.setAuthorityId(object.getAuthorityId());
        authority.setAuthorityCode(object.getAuthorityCode());
        authority.setServiceId(object.getServiceId());
        authority.setRequestMethod(object.getRequestMethod());
        authority.setUrl(object.getUrl());
        return authority;
    }
}
