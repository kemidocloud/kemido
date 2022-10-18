package com.kemido.nosql.couchdb.definition;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.assistant.core.definition.http.AbstractRest;
import com.kemido.nosql.couchdb.properties.CouchdbProperties;
import cn.hutool.core.codec.Base64;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: CouchDB 基础服务 </p>
 */
public abstract class AbstractCouchdbService extends AbstractRest {

    private static final Logger log = LoggerFactory.getLogger(AbstractCouchdbService.class);
    @Autowired
    private CouchdbProperties couchdbProperties;

    @Override
    protected String getBaseUrl() {
        return couchdbProperties.getEndpoint();
    }

    private String getBasicToken() {
        Assert.hasText(couchdbProperties.getUsername(), "username cannot be empty");
        Assert.hasText(couchdbProperties.getPassword(), "password cannot be empty");
        String content = couchdbProperties.getUsername() + SymbolConstants.COLON + couchdbProperties.getPassword();
        String token = BaseConstants.BASIC_TOKEN + Base64.encode(content);
        log.debug("[Kemido] |- Create CouchDB Basic Authentication Token : [{}]", token);
        return token;
    }

    protected Map<String, String> getBasicAuthentication() {
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, getBasicToken());
        return header;
    }
}
