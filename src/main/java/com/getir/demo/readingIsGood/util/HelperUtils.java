package com.getir.demo.readingIsGood.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperUtils {

    public static boolean isValidEmailId(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public static LocalDate convertLocalDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate start = LocalDate.parse(date, format);
        return start;
    }
}
