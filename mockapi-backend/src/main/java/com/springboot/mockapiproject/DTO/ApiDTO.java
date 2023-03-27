package com.springboot.mockapiproject.DTO;

import lombok.Data;

@Data
public class ApiDTO {
    private String mockName;
    private String url;
    private String operation;
    private Long responseID;
    private String response;
}
