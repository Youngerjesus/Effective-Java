package me.study.effectivejava.item5;

import java.util.List;

public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = dictionary;
    }

    // 내부 구현은 표현하지 않겠습니다.
    public boolean isValid(String word) {return true;}
    public List<String> suggestions(String type){ return null;}
}
