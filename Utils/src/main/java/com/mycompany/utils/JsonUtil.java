/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author tuantv
 */
public class JsonUtil {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    
    public static <T> T toObject(String input, Class<T> type) {
        if (!StringUtil.validString(input) || type == null) {
            return null;
        }
        
        try {
            return OBJECT_MAPPER.readValue(input, type);
        } catch (IOException ex) {
        }
        
        return null;
    }
    
    public static <T> T toObject(InputStream inputStream, Class<T> type) {
        if (inputStream == null || type == null) {
            return null;
        }
        
        return OBJECT_MAPPER.convertValue(inputStream, type);
    }
    
}
