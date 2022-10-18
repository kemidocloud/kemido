package com.kemido.oauth2.metadata.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: URL 路径类别 </p>
 */
public enum Category {

    /**
     * 含有通配符，含有 "*" 或 "?"
     */
    WILDCARD,
    /**
     * 含有占位符，含有 "{" 和 " } "
     */
    PLACEHOLDER,
    /**
     * 不含有任何特殊字符的完整路径
     */
    FULL_PATH;

    public static Category getCategory(String url) {

        if (StringUtils.containsAny(url, new String[]{"*", "?"})) {
            return Category.WILDCARD;
        }

        if (StringUtils.contains(url, "{")) {
            return Category.PLACEHOLDER;
        }

        return Category.FULL_PATH;
    }
}
