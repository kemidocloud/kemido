package com.kemido.assistant.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 权限资源类型 </p>
 */
@Schema(title = "权限类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityType {

    /**
     * enum
     */
    API(0, "REST API"),
    MENU(1, "功能菜单"),
    PAGE(2, "Web Page"),
    MINI_PAGE(3, "小程序页面");

    @Schema(title = "枚举值")
    private final Integer index;
    @Schema(title = "文字")
    private final String text;

    private static final Map<Integer, AuthorityType> indexMap = new HashMap<>();
    private static final List<Map<String, Object>> toJsonStruct = new ArrayList<>();

    static {
        for (AuthorityType authorityType : AuthorityType.values()) {
            indexMap.put(authorityType.getIndex(), authorityType);
            toJsonStruct.add(authorityType.getIndex(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", authorityType.getIndex())
                            .put("key", authorityType.name())
                            .put("text", authorityType.getText())
                            .build());
        }
    }

    AuthorityType(Integer index, String text) {
        this.index = index;
        this.text = text;
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
    public Integer getIndex() {
        return index;
    }

    public String getText() {
        return this.text;
    }

    public static AuthorityType getAuthorityType(Integer index) {
        return indexMap.get(index);
    }

    public static List<Map<String, Object>> getToJsonStruct() {
        return toJsonStruct;
    }
}
