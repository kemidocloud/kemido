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
 * <p>Description: 令牌格式 </p>
 */
@Schema(name = "令牌格式")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TokenFormat implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    SELF_CONTAINED(0,"self-contained", "自包含格式令牌"),
    REFERENCE(1,"reference", "引用（不透明）令牌");

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "格式")
    private final String format;
    @Schema(title = "文字")
    private final String description;

    private static final Map<Integer, TokenFormat> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (TokenFormat tokenFormat : TokenFormat.values()) {
            INDEX_MAP.put(tokenFormat.getValue(), tokenFormat);
            JSON_STRUCTURE.add(tokenFormat.getValue(),
                    ImmutableMap.<String, Object>builder()
                            // 使用数字作为 value, 适用于单选，同时数据库只存 value值即可
                            // 使用具体的字符串值作为value, 适用于多选，同时数据库存储以逗号分隔拼接的字符串
                            .put("value", tokenFormat.getValue())
                            .put("key", tokenFormat.name())
                            .put("text", tokenFormat.getDescription())
                            .put("format", tokenFormat.getFormat())
                            .put("index", tokenFormat.ordinal())
                            .build());
        }
    }

    TokenFormat(Integer value, String method, String description) {
        this.value = value;
        this.format = method;
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

    public String getFormat() {
        return format;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static TokenFormat get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }
}
