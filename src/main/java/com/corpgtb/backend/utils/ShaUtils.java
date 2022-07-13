package com.corpgtb.backend.utils;

public class ShaUtils {
    public static String codificarTexto(String texto) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(texto);
    }
}
