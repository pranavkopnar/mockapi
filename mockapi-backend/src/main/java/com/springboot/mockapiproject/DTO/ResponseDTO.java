package com.springboot.mockapiproject.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseDTO {
    private Long rid;
    private Map<String, Object> response;
}
