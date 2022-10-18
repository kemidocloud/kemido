package com.kemido.captcha.graphic.renderer;

import com.kemido.assistant.core.utils.RegexPool;
import com.kemido.captcha.graphic.definition.AbstractBaseGraphicRenderer;
import com.kemido.captcha.core.definition.domain.Metadata;
import com.kemido.captcha.core.definition.enums.CaptchaCategory;
import com.kemido.captcha.core.provider.RandomProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.image.BufferedImage;

/**
 * <p>Description: 算数类型一般验证码 </p>
 */
@Component
public class ArithmeticCaptchaRenderer extends AbstractBaseGraphicRenderer {

    private static final Logger log = LoggerFactory.getLogger(ArithmeticCaptchaRenderer.class);

    private int complexity = 2;
    /**
     * 计算结果
     */
    private String computedResult;

    @Override
    public String getCategory() {
        return CaptchaCategory.ARITHMETIC.getConstant();
    }

    @Override
    protected String getBase64ImagePrefix() {
        return BASE64_PNG_IMAGE_PREFIX;
    }

    @Override
    protected String[] getDrawCharacters() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < complexity; i++) {
            builder.append(RandomProvider.randomInt(10));
            if (i < complexity - 1) {
                int type = RandomProvider.randomInt(1, 4);
                if (type == 1) {
                    builder.append("+");
                } else if (type == 2) {
                    builder.append("-");
                } else if (type == 3) {
                    builder.append("x");
                }
            }
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        try {
            computedResult = String.valueOf(engine.eval(builder.toString().replaceAll("x", "*")));
        } catch (ScriptException e) {
            log.error("[Kemido] |- Arithmetic png captcha eval expression error！", e);
        }

        builder.append("=?");

        String result = builder.toString();
        return result.split(RegexPool.ALL_CHARACTERS);
    }

    @Override
    public Metadata draw() {
        String[] drawContent = getDrawCharacters();
        BufferedImage bufferedImage = createArithmeticBufferedImage(drawContent);

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(toBase64(bufferedImage));
        metadata.setCharacters(this.computedResult);
        return metadata;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.complexity = this.getCaptchaProperties().getGraphics().getComplexity();
    }
}
