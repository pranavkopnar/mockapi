package com.springboot.mockapiproject.DTO;

import lombok.Data;

@Data
public class EndPointDTO {
    private String url;
    private String operation;
    private ResponseDTO response;
}
