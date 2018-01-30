package com.MyApp.util;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class BaseUtil {

    public static String getUserToken() {
        return UUID.randomUUID().toString();
    }
}
