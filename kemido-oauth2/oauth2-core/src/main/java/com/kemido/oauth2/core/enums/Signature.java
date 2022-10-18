package com.kemido.oauth2.core.enums;

import com.kemido.assistant.core.definition.enums.BaseUiEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: OAuth2 Signature </p>
 */
@Schema(name = "签名算法")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Signature implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    RS256(0, "RS256"),
    RS384(1, "RS384"),
    RS512(2,"RS512"),
    ES256(3, "ES256"),
    ES384(4, "ES384"),
    ES512(5, "ES512"),
    PS256(6, "PS256"),
    PS384(7, "PS384"),
    PS512(8, "PS512");

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(name = "文字")
    private final String description;

    private static final Map<Integer, Signature> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (Signature signature : Signature.values()) {
            INDEX_MAP.put(signature.getValue(), signature);
            JSON_STRUCTURE.add(signature.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", signature.getValue())
                            .put("key", signature.name())
                            .put("text", signature.getDescription())
                            .put("index", signature.getValue())
                            .build());
        }
    }

    Signature(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 不加@JsonValue，转换的时候转换出完整的对象。
     * 加了@JsonValue，只会显示相应的属性的值
     * <p>
     * 不使用@JsonValue @JsonDeserializer类里面要做相应的处理
     *
     * @return Enum枚举值
     */
    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static Signature get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }
}
