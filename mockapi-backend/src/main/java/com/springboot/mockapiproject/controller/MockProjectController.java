package com.springboot.mockapiproject.controller;

import com.springboot.mockapiproject.DTO.MockDTO;
import com.springboot.mockapiproject.service.impl.MockProjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/1.0")
public class MockProjectController {

    private MockProjectServiceImpl mockProjectService;


    public MockProjectController(MockProjectServiceImpl mockProjectService) {
        this.mockProjectService = mockProjectService;
    }

    @PostMapping("/createmock")
    public ResponseEntity<?> createMockProject(@RequestBody MockDTO mockDTO) {
        return new ResponseEntity<>(mockProjectService.createMockProject(mockDTO), HttpStatus.OK);
    }
}
