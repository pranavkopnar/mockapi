package com.springboot.mockapiproject.response;

import com.springboot.mockapiproject.DTO.ApiDTO;
import lombok.Data;

@Data
public class ApiResponse {
    private Long Id;
    private ApiDTO apiDTO;
}
