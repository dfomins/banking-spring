package com.bankapp.bank.utils;

import java.util.Random;
import java.util.UUID;

public class GeneratorUtils {
    public static String generateCardNumber() {
//        Random random = new Random();
//        StringBuilder cardNumber = new StringBuilder();
//
//        cardNumber.append("FOMDAN");
//
//        for (int i = 0; i < 5; i++) {
//            cardNumber.append(random.nextInt(10));
//        }
//
//        int sum = 0;
//        for (int i = 0; i < 5; i++) {
//            int digit = Character.getNumericValue(cardNumber.charAt(i));
//            if (i % 2 == 0) {
//                digit *= 2;
//                if (digit > 9) {
//                    digit -= 9;
//                }
//            }
//            sum += digit;
//        }
//
//        int checksum = (10 - (sum % 10)) % 10;
//        cardNumber.append(checksum);
//
//        return cardNumber.toString();
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        return "FOMDAN_" + uuid;
    }
}
