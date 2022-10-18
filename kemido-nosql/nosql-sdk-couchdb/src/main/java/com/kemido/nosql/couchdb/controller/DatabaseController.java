package com.kemido.nosql.couchdb.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.nosql.couchdb.domain.Response;
import com.kemido.nosql.couchdb.dto.Database;
import com.kemido.nosql.couchdb.service.DatabaseService;
import com.kemido.rest.core.controller.Controller;
import com.kemido.rest.core.definition.dto.Pager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description: CouchDB 数据库管理接口 </p>
 */
@RestController
@RequestMapping("/couchdb/database")
@Tags({
        @Tag(name = "Nosql 管理接口"),
        @Tag(name = "CouchDB 管理接口"),
        @Tag(name = "CouchDB Database 接口"),
})
public class DatabaseController implements Controller {

    private final DatabaseService databaseService;

    @Autowired
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Operation(summary = "创建数据库", description = "创建 CouchDB 数据库名称",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "Result", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "database", required = true, description = "CouchDB 数据库名称", schema = @Schema(implementation = Database.class))
    })
    @PostMapping
    public Result<Response> create(@Validated @RequestBody Database database) {
        Response response =  databaseService.create(database.getName());
        return result(response);
    }
}
