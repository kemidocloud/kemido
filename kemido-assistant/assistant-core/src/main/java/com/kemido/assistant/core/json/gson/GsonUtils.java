package com.kemido.assistant.core.json.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: Gson 工具类 </p>
 */
public class GsonUtils {

    private static volatile Gson instance;
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

    static {
        GSON_BUILDER.enableComplexMapKeySerialization();
        GSON_BUILDER.serializeNulls();
        GSON_BUILDER.setDateFormat("yyyy-MM-dd HH:mm:ss");
        GSON_BUILDER.disableHtmlEscaping();
    }

    private GsonUtils() {

    }

    public static Gson getInstance() {

        if (ObjectUtils.isEmpty(instance)) {
            synchronized (GSON_BUILDER) {
                if (ObjectUtils.isEmpty(instance)) {
                    instance = GSON_BUILDER.create();
                }

            }
        }

        return instance;
    }

    public static JsonElement toJsonElement(String content) {
        return JsonParser.parseString(content);
    }

    public static JsonArray toJsonArray(String content) {
        return toJsonElement(content).getAsJsonArray();
    }

    public static JsonObject toJsonObject(String content) {
        return toJsonElement(content).getAsJsonObject();
    }

    public static <T> String toJson(T domain) {
        return getInstance().toJson(domain);
    }

    /**
     * 将 json 转化为 对象
     *
     * @param content   json 字符串
     * @param valueType 目标对象类型
     * @param <T>       对象类型
     * @return 转换后的对象
     */
    public static <T> T toObject(String content, Class<T> valueType) {
        return getInstance().fromJson(content, valueType);
    }

    /**
     * 将 json 转化为 对象
     * <p>
     * new TypeToken<List<T>>() {}.getType()
     * new TypeToken<Map<String, T>>() {}.getType()
     * new TypeToken<List<Map<String, T>>>() {}.getType()
     *
     * @param content json 字符串
     * @param typeOfT 目标对象类型
     * @param <T>     对象类型
     * @return 转换后的对象
     */
    public static <T> T toObject(String content, Type typeOfT) {
        return getInstance().fromJson(content, typeOfT);
    }

    public static <T> T toList(String content, Class<T> valueType) {
        return getInstance().fromJson(content, new TypeToken<List<T>>() {
        }.getType());
    }

    public static <T> List<Map<String, T>> toListMap(String content) {
        return getInstance().fromJson(content, new TypeToken<List<Map<String, String>>>() {
        }.getType());
    }

    public static <T> Map<String, T> toMaps(String gsonString) {
        return getInstance().fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
    }

}
