package com.corpgtb.backend.utils;

import java.util.Date;

public class FechaUtils {
    public static Date getFechaHoyIni() {
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }
    public static Date getFechaHoyFin() {
        Date date = new Date();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    public static Date inicioDia(Date fecha) {
        Date date = new Date(fecha.getTime());
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    public static Date finDia(Date fecha) {
        Date date = new Date(fecha.getTime());
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }
}
