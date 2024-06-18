package com.chan.grpc_callpy.util;

import java.util.Base64;

public class Base64Utils {

    public static String base64Decode(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new String(decodedBytes);
    }
}
