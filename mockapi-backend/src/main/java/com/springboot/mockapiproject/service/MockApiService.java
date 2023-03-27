package com.springboot.mockapiproject.service;

import java.util.List;
import java.util.Map;

public interface MockApiService {
    Map<String, Object> mockApiGet(String url, Long id);
    List<Map<String, Object>> mockApiGetAll(String url);
}
