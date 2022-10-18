package com.kemido.assistant.core.json.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>Description: 数组转字符串序列化 </p>
 */
public class ArrayOrStringDeserializer extends StdDeserializer<Set<String>> {

    public ArrayOrStringDeserializer() {
        super(Set.class);
    }

    public JavaType getValueType() {
        return TypeFactory.defaultInstance().constructType(String.class);
    }

    @Override
    public Set<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonToken token = jp.getCurrentToken();
        if (token.isScalarValue()) {
            String list = jp.getText();
            list = list.replaceAll("\\s+", ",");
            return new LinkedHashSet(Arrays.asList(StringUtils.commaDelimitedListToStringArray(list)));
        } else {
            return jp.readValueAs(new TypeReference<Set<String>>() {
            });
        }
    }


}
