package com.kemido.captcha.graphic.definition;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.captcha.core.definition.domain.Metadata;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.gif.AnimatedGifEncoder;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.stream.IntStream;

/**
 * <p>Description: Gif 类型图形验证码绘制器 </p>
 */
public abstract class AbstractGifGraphicRenderer extends AbstractBaseGraphicRenderer {

    @Override
    protected String getBase64ImagePrefix() {
        return BASE64_GIF_IMAGE_PREFIX;
    }

    @Override
    public Metadata draw() {

        String[] drawCharacters = this.getDrawCharacters();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        // gif编码类
        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
        // 生成字符
        gifEncoder.start(out);
        // 设置量化器取样间隔
        gifEncoder.setQuality(180);
        // 帧延迟 (默认100)
        int delay = 100;
        //设置帧延迟
        gifEncoder.setDelay(delay);
        //帧循环次数
        gifEncoder.setRepeat(0);

        IntStream.range(0, drawCharacters.length).forEach(i -> {
            BufferedImage frame = createGifBufferedImage(drawCharacters, i);
            gifEncoder.addFrame(frame);
            frame.flush();
        });

        gifEncoder.finish();

        String characters = StringUtils.join(drawCharacters, SymbolConstants.BLANK);

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(getBase64ImagePrefix() + Base64.encode(out.toByteArray()));
        metadata.setCharacters(characters);
        return metadata;
    }
}
