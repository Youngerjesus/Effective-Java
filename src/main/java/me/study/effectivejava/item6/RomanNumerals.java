package me.study.effectivejava.item6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$\n");


    static boolean isRomanNumeralGoodVersion(String s){
        return ROMAN.matcher(s).matches();
    }
    // Bad Version 입니다.
    static boolean isRomanNumeralBadVersion(String s){
        return s.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$\n");
    }
}
