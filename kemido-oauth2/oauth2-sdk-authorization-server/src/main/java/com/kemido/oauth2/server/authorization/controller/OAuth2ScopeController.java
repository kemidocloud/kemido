package com.kemido.oauth2.server.authorization.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.data.core.service.WriteableService;
import com.kemido.oauth2.server.authorization.dto.OAuth2AuthorityDto;
import com.kemido.oauth2.server.authorization.dto.OAuth2ScopeDto;
import com.kemido.oauth2.server.authorization.entity.OAuth2Authority;
import com.kemido.oauth2.server.authorization.entity.OAuth2Scope;
import com.kemido.oauth2.server.authorization.service.OAuth2ScopeService;
import com.kemido.protect.core.annotation.AccessLimited;
import com.kemido.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> Description : OauthScopesController </p>
 */
@RestController
@RequestMapping("/authorize/scope")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 权限范围管理接口")
})
public class OAuth2ScopeController extends BaseWriteableRestController<OAuth2Scope, String> {

    private final OAuth2ScopeService scopeService;

    @Autowired
    public OAuth2ScopeController(OAuth2ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    @Override
    public WriteableService<OAuth2Scope, String> getWriteableService() {
        return this.scopeService;
    }

    @Operation(summary = "给Scope分配权限", description = "给Scope分配权限",
            responses = {
                    @ApiResponse(description = "查询到的角色", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OAuth2ScopeDto.class))),
            })
    @Parameters({
            @Parameter(name = "scope", required = true, description = "范围请求参数"),
    })
    @PostMapping("/assigned")
    public Result<OAuth2Scope> assigned(@RequestBody OAuth2ScopeDto scope) {

        Set<OAuth2Authority> authorities = new HashSet<>();
        if (CollectionUtils.isNotEmpty(scope.getAuthorities())) {
            authorities = scope.getAuthorities().stream().map(this::toEntity).collect(Collectors.toSet());
        }

        OAuth2Scope result = scopeService.authorize(scope.getScopeId(), authorities);
        return result(result);
    }

    @AccessLimited
    @Operation(summary = "获取全部范围", description = "获取全部范围")
    @GetMapping("/list")
    public Result<List<OAuth2Scope>> findAll() {
        List<OAuth2Scope> oAuth2Scopes = scopeService.findAll();
        return result(oAuth2Scopes);
    }

    @AccessLimited
    @Operation(summary = "根据范围代码查询应用范围", description = "根据范围代码查询应用范围",
            responses = {
                    @ApiResponse(description = "查询到的应用范围", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OAuth2Scope.class))),
                    @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
                    @ApiResponse(responseCode = "500", description = "查询失败")
            }
    )
    @GetMapping("/{scopeCode}")
    public Result<OAuth2Scope> findByUserName(@PathVariable("scopeCode") String scopeCode) {
        OAuth2Scope scope = scopeService.findByScopeCode(scopeCode);
        return result(scope);
    }

    private OAuth2Authority toEntity(OAuth2AuthorityDto dto) {
        OAuth2Authority entity = new OAuth2Authority();
        entity.setAuthorityId(dto.getAuthorityId());
        entity.setAuthorityCode(dto.getAuthorityCode());
        entity.setServiceId(dto.getServiceId());
        entity.setRequestMethod(dto.getRequestMethod());
        entity.setUrl(dto.getUrl());
        return entity;
    }

}
