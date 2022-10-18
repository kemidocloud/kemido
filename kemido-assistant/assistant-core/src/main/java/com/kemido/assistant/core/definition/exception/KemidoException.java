package com.kemido.assistant.core.definition.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.domain.Result;

/**
 * <p>Description: 核心 KemidoException 定义 </p>
 */
public interface KemidoException {

    /**
     * 获取反馈信息
     *
     * @return 反馈信息对象 {@link Feedback}
     */
    Feedback getFeedback();

    /**
     * 错误信息转换为 Result 对象。
     *
     * @return 结果对象 {@link Result}
     */
    Result<String> getResult();
}
