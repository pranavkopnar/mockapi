package com.springboot.mockapiproject.controller;

import com.springboot.mockapiproject.service.impl.MockApiServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MockApiController {
    private MockApiServiceImpl mockApiService;

    public MockApiController(MockApiServiceImpl mockApiService) {
        this.mockApiService = mockApiService;
    }


    @GetMapping("/**")
    public ResponseEntity<?> mockAPIGet(HttpServletRequest request) {

        String endpoint = request.getRequestURI().split(request.getContextPath() + "/api/v1/")[1];

        String[] urlSplit = endpoint.split("/");

        if (urlSplit.length == 2) {
            List<Map<String, Object>> responses = null;

            responses = mockApiService.mockApiGetAll(request.getRequestURI().split(request.getContextPath() + "/api/v1/")[1]);

            if (responses == null) {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(responses, HttpStatus.FOUND);
        } else {
            Map<String, Object> response = null;
            System.out.println(urlSplit[0] + "/" + urlSplit[1] + "/" + ":id");
            System.out.println(Long.parseLong(urlSplit[2]));
            response = mockApiService.mockApiGet(urlSplit[0] + "/" + urlSplit[1] + "/" + ":id", Long.parseLong(urlSplit[2]));

            if (response == null) {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

    }

//    @PostMapping("/**")
//    public ResponseEntity<?> mockAPI2(HttpServletRequest request, @RequestBody Map<String, Object> data) {
//        Map<String, Object> response = mockApiService.mockApi(request.getRequestURI().split(request.getContextPath() + "/api/v1/")[1], "POST");
//
//        if (response == null) {
//            return new ResponseEntity<>("Cannot save data", HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
