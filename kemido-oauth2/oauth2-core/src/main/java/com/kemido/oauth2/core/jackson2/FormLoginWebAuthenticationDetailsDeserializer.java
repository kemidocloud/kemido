package com.kemido.oauth2.core.jackson2;

import com.kemido.oauth2.core.definition.domain.FormLoginWebAuthenticationDetails;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;

/**
 * <p>Description: FormLoginWebAuthenticationDetailsDeserializer </p>
 */
public class FormLoginWebAuthenticationDetailsDeserializer extends JsonDeserializer<FormLoginWebAuthenticationDetails> {
    @Override
    public FormLoginWebAuthenticationDetails deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);

        String remoteAddress = readJsonNode(jsonNode, "remoteAddress").asText();
        String sessionId = readJsonNode(jsonNode, "sessionId").asText();
        String parameterName = readJsonNode(jsonNode, "parameterName").asText();
        String category = readJsonNode(jsonNode, "category").asText();
        String code = readJsonNode(jsonNode, "code").asText();
        String identity = readJsonNode(jsonNode, "identity").asText();
        boolean closed = readJsonNode(jsonNode, "closed").asBoolean();

        return new FormLoginWebAuthenticationDetails(remoteAddress, sessionId, closed, parameterName, category, code, identity);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
