package com.kemido.captcha.hutool.renderer;

import com.kemido.captcha.core.definition.AbstractGraphicRenderer;
import com.kemido.captcha.core.definition.domain.Metadata;
import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

/**
 * <p>Description: Hutool圆圈干扰验证码绘制器 </p>
 */
public class CircleCaptchaRenderer extends AbstractGraphicRenderer {

    @Override
    public Metadata draw() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(this.getWidth(), this.getHeight(), this.getLength(), 20);
        circleCaptcha.setFont(this.getFont());

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(circleCaptcha.getImageBase64Data());
        metadata.setCharacters(circleCaptcha.getCode());
        return metadata;
    }

    @Override
    public String getCategory() {
        return CaptchaCategory.HUTOOL_CIRCLE.getConstant();
    }
}
