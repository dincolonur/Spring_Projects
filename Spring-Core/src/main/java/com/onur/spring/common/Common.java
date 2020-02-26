package com.onur.spring.common;

public class Common {

    public static boolean isDateValidFormat(String value) {
        try {
            String[] values = value.split("-");
            if(values.length != 3){
                return false;
            }
            if (((Integer.parseInt(values[0]) <= 1900) || (Integer.parseInt(values[0]) >= 2100))
                    || ((Integer.parseInt(values[1]) < 1) || (Integer.parseInt(values[1]) > 12))
                    || ((Integer.parseInt(values[2]) < 1) || (Integer.parseInt(values[2]) > 31)) ) {
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
