package com.example.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String source) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(source);
            System.out.println(date);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
