package com.kemido.sms.huawei.processor;

import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import com.kemido.sms.huawei.domain.HuaweiSmsRequest;
import com.kemido.sms.huawei.domain.HuaweiSmsResponse;
import com.kemido.sms.huawei.properties.HuaweiSmsProperties;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Description: 华为云发送处理 </p>
 */
public class HuaweiSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(HuaweiSmsSendHandler.class);

    /**
     * 无需修改,用于格式化鉴权头域,给"X-WSSE"参数赋值
     */
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";

    /**
     * 无需修改,用于格式化鉴权头域,给"Authorization"参数赋值
     */
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

    private final HuaweiSmsProperties properties;

    public HuaweiSmsSendHandler(HuaweiSmsProperties properties) {
        super(properties);
        this.properties = properties;
    }


    private CloseableHttpClient buildHttpclient() {
        try {
            TrustStrategy trustStrategy = (x509CertChain, authType) -> true;
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStrategy).build();

            return HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 构造X-WSSE参数值
     *
     * @return X-WSSE参数值
     */
    private String buildWsseHeader() {
        String appKey = this.properties.getAppKey();
        String appSecret = this.properties.getAppSecret();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        String nonce = UUID.randomUUID().toString().replace("-", "");

        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
        String hexDigest = Hex.encodeHexString(passwordDigest);

        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(hexDigest.getBytes());

        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.HUAWEI_CLOUD.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {

        String templateId = this.getTemplateId(template);
        String mobiles = this.wellFormed(phones);
        String templateParams = this.getOrderedParamsString(template);

        String wsseHeader = buildWsseHeader();

        HuaweiSmsRequest request = new HuaweiSmsRequest();
        request.setFrom(this.properties.getSender());
        request.setTo(mobiles);
        request.setTemplateId(templateId);
        request.setTemplateParas(templateParams);
        request.setSignature(this.properties.getSignature());


        HttpResult result = this.http().sync(this.properties.getUri())
                .bodyType(OkHttps.FORM)
                .addHeader(HttpHeaders.AUTHORIZATION, AUTH_HEADER_VALUE)
                .addHeader("X-WSSE", wsseHeader)
                .setBodyPara(request)
                .nothrow()
                .post();

        if (result.isSuccessful()) {
            HuaweiSmsResponse huaweiSmsResponse = result.getBody().toBean(HuaweiSmsResponse.class);
            if (ObjectUtils.isNotEmpty(huaweiSmsResponse) && HuaweiSmsResponse.SUCCESS_CODE.equals(huaweiSmsResponse.getCode())) {
                return true;
            }
        }

        return false;
    }
}
