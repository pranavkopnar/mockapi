package com.springboot.mockapiproject.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;



public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private ObjectMapper objectMapper;

    public HashMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {

        String dataJson = null;
        try {
            dataJson = objectMapper.writeValueAsString(stringObjectMap);
        } catch (final JsonProcessingException e) {
            System.out.println("JSON error" + e);
        }

        return dataJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {

        Map<String, Object> dataSchema = null;
        try {
            dataSchema = objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (final IOException e) {
            System.out.println("JSON error" + e);
        }

        return dataSchema;
    }
}
