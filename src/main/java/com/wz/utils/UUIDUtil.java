package com.wz.utils;
import java.util.Date;
import java.util.UUID;
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static Date calculateExpiryTime(int validityMinutes) {
        return new Date(System.currentTimeMillis() + validityMinutes * 60 * 1000);
    }
}

