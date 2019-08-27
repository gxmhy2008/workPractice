package com.gxm.workPractice.common.util;

import java.util.UUID;

public class UUIDUtils {
    public static String uuidKey() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
