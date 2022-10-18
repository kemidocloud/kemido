package com.kemido.captcha.hutool.renderer;

import com.kemido.captcha.core.definition.AbstractGraphicRenderer;
import com.kemido.captcha.core.definition.domain.Metadata;
import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;

/**
 * <p>Description: Hutool扭曲干扰验证码绘制器 </p>
 */
public class ShearCaptchaRenderer extends AbstractGraphicRenderer {

    @Override
    public Metadata draw() {
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(this.getWidth(), this.getHeight(), this.getLength(), 4);
        shearCaptcha.setFont(this.getFont());

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(shearCaptcha.getImageBase64Data());
        metadata.setCharacters(shearCaptcha.getCode());
        return metadata;
    }

    @Override
    public String getCategory() {
        return CaptchaCategory.HUTOOL_SHEAR.getConstant();
    }
}
