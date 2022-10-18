package com.kemido.assistant.core.constants;

import org.apache.http.HttpStatus;

/**
 * <p>Description: 错误代码计数器 </p>
 * <p>
 * 都需要手动控制错误代码，通过代码的继承关系，方便的查看以及编写。
 * <p>
 * 1**	信息，服务器收到请求，需要请求者继续执行操作
 * 2**	成功，操作被成功接收并处理
 * 3**	重定向，需要进一步的操作以完成请求
 * 4**	客户端错误，请求包含语法错误或无法完成请求
 * 5**	服务器错误，服务器在处理请求的过程中发生了错误
 * <p>
 * 1开头的状态码
 * 100	Continue	继续。客户端应继续其请求
 * 101	Switching Protocols	切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议
 * <p>
 * 2开头的状态码
 * 200	OK	请求成功。一般用于GET与POST请求
 * 201	Created	已创建。成功请求并创建了新的资源
 * 202	Accepted	已接受。已经接受请求，但未处理完成
 * 203	Non-Authoritative Information	非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本
 * 204	No Content	无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
 * 205	Reset Content	重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域
 * 206	Partial Content	部分内容。服务器成功处理了部分GET请求
 * <p>
 * 3开头的状态码
 * 300	Multiple Choices	多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择
 * 301	Moved Permanently	永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替
 * 302	Found	临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI
 * 303	See Other	查看其它地址。与301类似。使用GET和POST请求查看
 * 304	Not Modified	未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源
 * 305	Use Proxy	使用代理。所请求的资源必须通过代理访问
 * 306	Unused	已经被废弃的HTTP状态码
 * 307	Temporary Redirect	临时重定向。与302类似。使用GET请求重定向
 * <p>
 * 4开头的状态码
 * 400	Bad Request	客户端请求的语法错误，服务器无法理解
 * 401	Unauthorized	请求要求用户的身份认证
 * 402	Payment Required	保留，将来使用
 * 403	Forbidden	服务器理解请求客户端的请求，但是拒绝执行此请求
 * 404	Not Found	服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
 * 405	Method Not Allowed	客户端请求中的方法被禁止
 * 406	Not Acceptable	服务器无法根据客户端请求的内容特性完成请求
 * 407	Proxy Authentication Required	请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权
 * 408	Request Time-out	服务器等待客户端发送的请求时间过长，超时
 * 409	Conflict	服务器完成客户端的PUT请求是可能返回此代码，服务器处理请求时发生了冲突
 * 410	Gone	客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置
 * 411	Length Required	服务器无法处理客户端发送的不带Content-Length的请求信息
 * 412	Precondition Failed	客户端请求信息的先决条件错误
 * 413	Request Entity Too Large	由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息
 * 414	Request-URI Too Large	请求的URI过长（URI通常为网址），服务器无法处理
 * 415	Unsupported Media Type	服务器无法处理请求附带的媒体格式
 * 416	Requested range not satisfiable	客户端请求的范围无效
 * 417	Expectation Failed	服务器无法满足Expect的请求头信息
 * <p>
 * 5开头的状态码
 * 500	Internal Server Error	服务器内部错误，无法完成请求
 * 501	Not Implemented	服务器不支持请求的功能，无法完成请求
 * 502	Bad Gateway	充当网关或代理的服务器，从远端服务器接收到了一个无效的请求
 * 503	Service Unavailable	由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
 * 504	Gateway Time-out	充当网关或代理的服务器，未及时从远端服务器获取请求
 * 505	HTTP Version not supported	服务器不支持请求的HTTP协议的版本，无法完成处理
 * <p>
 */
public interface ErrorCode {

    /* ---------- 401 ---------- */
    int UNAUTHORIZED = HttpStatus.SC_UNAUTHORIZED * 100;
    int ACCESS_DENIED = UNAUTHORIZED + 1;
    int ACCOUNT_DISABLED = ACCESS_DENIED + 1;
    int ACCOUNT_ENDPOINT_LIMITED = ACCOUNT_DISABLED + 1;

    int ACCOUNT_EXPIRED = ACCOUNT_ENDPOINT_LIMITED + 1;
    int ACCOUNT_LOCKED = ACCOUNT_EXPIRED + 1;
    int BAD_CREDENTIALS = ACCOUNT_LOCKED + 1;
    int CREDENTIALS_EXPIRED = BAD_CREDENTIALS + 1;
    int INVALID_CLIENT = CREDENTIALS_EXPIRED + 1;
    int INVALID_TOKEN = INVALID_CLIENT + 1;
    int INVALID_GRANT = INVALID_TOKEN + 1;
    int UNAUTHORIZED_CLIENT = INVALID_TOKEN + 1;
    int USERNAME_NOT_FOUND = UNAUTHORIZED_CLIENT + 1;
    int SESSION_EXPIRED = USERNAME_NOT_FOUND + 1;

    /* ---------- 403 ---------- */
    int FORBIDDEN = HttpStatus.SC_FORBIDDEN * 100;
    int INSUFFICIENT_SCOPE = FORBIDDEN + 1;
    int SQL_INJECTION_REQUEST = INSUFFICIENT_SCOPE + 1;

    /* ---------- 405 ---------- */
    int METHOD_NOT_ALLOWED = HttpStatus.SC_METHOD_NOT_ALLOWED * 100;
    int HTTP_REQUEST_METHOD_NOT_SUPPORTED = METHOD_NOT_ALLOWED + 1;

    /* ---------- 406 ---------- */
    int NOT_ACCEPTABLE = HttpStatus.SC_NOT_ACCEPTABLE * 100;
    int UNSUPPORTED_GRANT_TYPE = NOT_ACCEPTABLE + 1;
    int UNSUPPORTED_RESPONSE_TYPE = UNSUPPORTED_GRANT_TYPE + 1;
    int UNSUPPORTED_TOKEN_TYPE = UNSUPPORTED_RESPONSE_TYPE + 1;

    int CACHE_MODULE_406_BEGIN = UNSUPPORTED_TOKEN_TYPE;
    int CACHE_MODULE_406_END = CACHE_MODULE_406_BEGIN + 4;
    int PROTECT_MODULE_406_BEGIN = CACHE_MODULE_406_END;
    int PROTECT_MODULE_406_END = CACHE_MODULE_406_BEGIN + 3;

    /* ---------- 412 ---------- */
    int PRECONDITION_FAILED = HttpStatus.SC_PRECONDITION_FAILED * 100;
    int INVALID_REDIRECT_URI = PRECONDITION_FAILED + 1;
    int INVALID_REQUEST = INVALID_REDIRECT_URI + 1;
    int INVALID_SCOPE = INVALID_REQUEST + 1;

    int ACCESS_MODULE_406_BEGIN = INVALID_SCOPE;

    /* ---------- 415 ---------- */
    int UNSUPPORTED_MEDIA_TYPE = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE * 100;
    int HTTP_MEDIA_TYPE_NOT_ACCEPTABLE = UNSUPPORTED_MEDIA_TYPE + 1;

    /* ---------- 500 ---------- */
    int INTERNAL_SERVER_ERROR = HttpStatus.SC_INTERNAL_SERVER_ERROR * 100;
    int SERVER_ERROR = INTERNAL_SERVER_ERROR + 1;
    int HTTP_MESSAGE_NOT_READABLE_EXCEPTION = SERVER_ERROR + 1;
    int ILLEGAL_ARGUMENT_EXCEPTION = HTTP_MESSAGE_NOT_READABLE_EXCEPTION + 1;
    int IO_EXCEPTION = ILLEGAL_ARGUMENT_EXCEPTION + 1;
    int MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = IO_EXCEPTION + 1;
    int NULL_POINTER_EXCEPTION = MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION + 1;
    int TYPE_MISMATCH_EXCEPTION = NULL_POINTER_EXCEPTION + 1;

    int OSS_MODULE_500_BEGIN = TYPE_MISMATCH_EXCEPTION;
    int OSS_MODULE_500_END = OSS_MODULE_500_BEGIN + 12;
    int SMS_MODULE_500_BEGIN = OSS_MODULE_500_END;
    int SMS_MODULE_500_END = OSS_MODULE_500_END + 2;

    /* ---------- 503 ---------- */
    int SERVICE_UNAVAILABLE = HttpStatus.SC_SERVICE_UNAVAILABLE * 100;
    int COOKIE_THEFT = SERVICE_UNAVAILABLE + 1;
    int INVALID_COOKIE = COOKIE_THEFT + 1;
    int PROVIDER_NOT_FOUND = INVALID_COOKIE + 1;
    int TEMPORARILY_UNAVAILABLE = PROVIDER_NOT_FOUND + 1;

    int WEB_MODULE_503_BEGIN = TEMPORARILY_UNAVAILABLE;
    int WEB_MODULE_503_END = WEB_MODULE_503_BEGIN + 1;

    /* ---------- 600 ---------- */
    int DATABASE = 60000;

    /* ---------- 601 数据库操作运行前校验 ---------- */
    int DATABASE_VALIDATION = DATABASE + 100;
    int METHOD_ARGUMENT_NOT_VALID = DATABASE_VALIDATION + 1;

    /* ---------- 602 数据库操作执行 ---------- */
    int DATABASE_EXECUTION = DATABASE_VALIDATION + 100;
    int BAD_SQL_GRAMMAR = DATABASE_EXECUTION + 1;
    int DATA_INTEGRITY_VIOLATION = BAD_SQL_GRAMMAR + 1;
    int TRANSACTION_ROLLBACK = DATA_INTEGRITY_VIOLATION + 1;

    /* ---------- 700 基础设施交互错误 ---------- */
    int BASIC_FACILITIES = DATABASE + 10000;

    /* ---------- 701 Redis 相关错误 ---------- */
    int REDIS = BASIC_FACILITIES + 100;
    int PIPELINE_INVALID_COMMANDS = REDIS + 1;
}
