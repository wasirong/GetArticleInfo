package com.dhl.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogPrint {
    public static String WriteInfoLog(String info) {

        return "["
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "] : "
                + info
                + ";";
    }
}
