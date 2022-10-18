package com.kemido.captcha.graphic.renderer;

import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.graphic.definition.AbstractPngGraphicRenderer;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 类型验证码绘制器 </p>
 */
@Component
public class SpecCaptchaRenderer extends AbstractPngGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.SPEC.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getCharCharacters();
    }
}
