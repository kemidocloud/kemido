package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssXmlParserException </p>
 */
public class OssXmlParserException extends PlatformException {

    public OssXmlParserException() {
        super();
    }

    public OssXmlParserException(String message) {
        super(message);
    }

    public OssXmlParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssXmlParserException(Throwable cause) {
        super(cause);
    }

    protected OssXmlParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_XML_PARSER, "对象存储 XML 解析出现错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
