package com.kemido.captcha.behavior.renderer;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.captcha.core.definition.domain.Coordinate;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: 文字点选信息混淆器 </p>
 */
public class WordClickObfuscator {
    /**
     * 文字点选验证码文字坐标信息列表
     */
    private final List<Coordinate> coordinates;
    /**
     * 文字点选验证码校验文字
     */
    private final List<String> words;

    private String wordString;

    public WordClickObfuscator(List<String> originalWords, List<Coordinate> originalCoordinates) {
        this.coordinates = new ArrayList<>();
        this.words = new ArrayList<>();
        this.execute(originalWords, originalCoordinates);
    }

    private void execute(List<String> originalWords, List<Coordinate> originalCoordinates) {

        int[] indexes = RandomUtil.randomInts(originalWords.size());

        Arrays.stream(indexes).forEach(value -> {
            this.words.add(this.words.size(), originalWords.get(value));
            this.coordinates.add(this.coordinates.size(), originalCoordinates.get(value));
        });

        this.wordString = StringUtils.join(getWords(), SymbolConstants.COMMA);
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public List<String> getWords() {
        return words;
    }

    public String getWordString() {
        return this.wordString;
    }
}
