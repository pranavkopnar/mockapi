package com.springboot.mockapiproject.response;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponse {
    private Map<String, Object> response;
    private Map<String, Object> data;
}
