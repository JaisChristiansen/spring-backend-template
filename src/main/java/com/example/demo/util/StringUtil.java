package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class StringUtil {
    public static boolean isNullOrEmpty(String s){
        return (s==null || s.isEmpty());
    }

    public static boolean isNullOrSpaces(String s){
        return (s==null || s.trim().isEmpty());
    }

    public static String generateRandomString(Integer length) {
        byte[] array = new byte[length == null ? 16 : length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public static String generateAlphanumericString() {
        return generateAlphanumericString(false, null);
    }

    public static String generateAlphanumericString(boolean includeSpecialCharacter) {
        return generateAlphanumericString(includeSpecialCharacter, null);
    }

    public static String generateAlphanumericString(Integer length) {
        return generateAlphanumericString(false, length);
    }

    public static String generateAlphanumericString(boolean includeSpecialCharacter, Integer length) {
        String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int actualLength = length == null || length <= 1 ? 16 : length;
        StringBuilder sb = new StringBuilder(actualLength);

        if (includeSpecialCharacter) {
            sb.append("!");
            actualLength -= 1;
        }

        Random random = new Random();
        for (int i = 0; i < actualLength; i++) {
            sb.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
        }
        return sb.toString();
    }

}
