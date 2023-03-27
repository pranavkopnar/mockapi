package com.springboot.mockapiproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mockapiproject.entity.converter.HashMapConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MockApiProjectApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HashMapConverter hashMapConverter() {
        return new HashMapConverter(objectMapper());
    }

    public static void main(String[] args) {
        SpringApplication.run(MockApiProjectApplication.class, args);
    }

}
