package com.nexthero.demo.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateJsonDeserializer  extends JsonDeserializer<Date> {
    public static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            if (jsonParser != null && jsonParser.getText() != null && !"".equals(jsonParser.getText())) {
                return new java.sql.Date(format.parse(jsonParser.getText()).getTime());
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
