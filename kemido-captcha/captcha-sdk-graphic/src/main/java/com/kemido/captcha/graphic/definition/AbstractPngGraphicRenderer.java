package com.kemido.captcha.graphic.definition;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.captcha.core.definition.domain.Metadata;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;

/**
 * <p>Description: Png 类型图形验证码绘制器 </p>
 */
public abstract class AbstractPngGraphicRenderer extends AbstractBaseGraphicRenderer {

    @Override
    public Metadata draw() {
        String[] drawCharacters = this.getDrawCharacters();

        BufferedImage bufferedImage = createPngBufferedImage(drawCharacters);

        String characters = StringUtils.join(drawCharacters, SymbolConstants.BLANK);

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(toBase64(bufferedImage));
        metadata.setCharacters(characters);

        return metadata;
    }
}
