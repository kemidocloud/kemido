package com.kemido.captcha.graphic.renderer;

import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.graphic.definition.AbstractGifGraphicRenderer;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Gif 类型验证码绘制器 </p>
 */
@Component
public class SpecGifCaptchaRenderer extends AbstractGifGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.SPEC_GIF.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getCharCharacters();
    }
}
