package com.kemido.nosql.couchdb.service;

import com.kemido.nosql.couchdb.definition.AbstractCouchdbService;
import com.kemido.nosql.couchdb.domain.Response;
import cn.zhxu.data.TypeRef;
import cn.zhxu.okhttps.OkHttps;
import org.springframework.stereotype.Service;

/**
 * <p>Description: CouchDB 数据库操作服务 </p>
 */
@Service
public class DatabaseService extends AbstractCouchdbService {

    public Response create(String name) {
        return this.http().sync("/{db}")
                .bodyType(OkHttps.JSON)
                .addHeader(this.getBasicAuthentication())
                .addPathPara("db", name)
                .put()
                .getBody()
                .toBean(new TypeRef<Response>() {
                });
    }
}
