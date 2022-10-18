package com.kemido.oauth2.core.jackson2;

import com.kemido.oauth2.core.definition.domain.KemidoGrantedAuthority;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;

/**
 * <p>Description: KemidoGrantedAuthority 反序列化 </p>
 */
public class KemidoGrantedAuthorityDeserializer extends JsonDeserializer<KemidoGrantedAuthority> {
    @Override
    public KemidoGrantedAuthority deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        String authority = readJsonNode(jsonNode, "authority").asText();
        return new KemidoGrantedAuthority(authority);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
