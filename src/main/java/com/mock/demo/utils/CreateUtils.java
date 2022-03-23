package com.mock.demo.utils;

import java.sql.Timestamp;

/**
 * @author SuongNguyen
 * @created 3/23/2022
 */

public class CreateUtils {

    public static String generateCode(Class<?> clazz) {
        return (clazz.getSimpleName() + "_" + new Timestamp(System.currentTimeMillis()))
                .replaceAll("-", "_")
                .replaceAll(":", "_")
                .replaceAll("\\.", "_")
                .replaceAll("\\s", "_");
    }
}
