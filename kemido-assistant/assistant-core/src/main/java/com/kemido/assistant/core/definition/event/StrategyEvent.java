package com.kemido.assistant.core.definition.event;

import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;

/**
 * <p>Description: 策略 Event 定义 </p>
 * <p>
 * 为了同时支持 分布式模式 和 单体式模式。所以很多事件均需要同时支持本地发送和远程发送两种模式。
 * 抽象本定义用于统一处理两种模式的事件支持。
 */
public interface StrategyEvent<T> {

    /**
     * 创建本地事件
     *
     * @param data 事件携带数据
     */
    void postLocalProcess(T data);

    /**
     * 创建远程事件
     *
     * @param data               事件携带数据。JSON 格式的数据。
     * @param originService      发送远程事件原始服务
     * @param destinationService 接收远程事件目的地
     */
    void postRemoteProcess(String data, String originService, String destinationService);

    /**
     * 是否是本地处理事件。
     *
     * @param destinationService 接收远程事件目的地
     * @return false 远程事件，local 本地事件
     */
    boolean isLocal(String destinationService);

    /**
     * 发送事件
     *
     * @param data               事件携带数据
     * @param originService      发送远程事件原始服务
     * @param destinationService 接收远程事件目的地
     */
    default void postProcess(String originService, String destinationService, T data) {
        if (isLocal(destinationService)) {
            postLocalProcess(data);
        } else {
            postRemoteProcess(JacksonUtils.toJson(data), originService, destinationService);
        }
    }
}
