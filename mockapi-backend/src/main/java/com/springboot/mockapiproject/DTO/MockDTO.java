package com.springboot.mockapiproject.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class MockDTO {
    private String mockName;
    private Map<String, Object> dataSchema;
    private EndPointDTO endPoint;
}
