package delivery.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class OrderGenerator {

    public static String generateRandomCustomerName() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateRandomCustomerPhone() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateRandomComment() {
        return RandomStringUtils.randomAlphabetic(15);
    }

}