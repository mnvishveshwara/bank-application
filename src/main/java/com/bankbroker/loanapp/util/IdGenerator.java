package com.bankbroker.loanapp.util;

import java.security.SecureRandom;

public class IdGenerator {

    private static final String ALPHANUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();


    public static String generateId(String prefix) {
        prefix = prefix.toUpperCase();

        // Ensure prefix is exactly 3 characters
        if (prefix.length() > 3) {
            prefix = prefix.substring(0, 3);
        } else if (prefix.length() < 3) {
            prefix = String.format("%-3s", prefix).replace(" ", "X");
        }

        // Generate remaining random chars (12 - 3 = 9)
        return prefix + generateRandom(9);
    }

    public static String generateId() {
        return generateId("GEN");
    }

    private static String generateRandom(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
}
