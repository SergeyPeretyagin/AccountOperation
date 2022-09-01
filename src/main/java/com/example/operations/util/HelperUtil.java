package com.example.operations.util;

import com.mifmif.common.regex.Generex;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelperUtil {
    public static String createNumberAccount(){
        Generex generex = new Generex("408\\d{17}");
        String number = generex.random();
        log.info("create {} ",number);
        return number;
    }
}
