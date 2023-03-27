package com.springboot.mockapiproject.service.impl;

import com.springboot.mockapiproject.DTO.EndPointDTO;
import com.springboot.mockapiproject.DTO.MockDTO;
import com.springboot.mockapiproject.DTO.ResponseDTO;
import com.springboot.mockapiproject.entity.Data;
import com.springboot.mockapiproject.entity.EndPoint;
import com.springboot.mockapiproject.entity.MockProject;
import com.springboot.mockapiproject.entity.Response;
import com.springboot.mockapiproject.entity.converter.HashMapConverter;
import com.springboot.mockapiproject.repository.DataRepository;
import com.springboot.mockapiproject.repository.MockProjectRepository;
import com.springboot.mockapiproject.service.MockProjectService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MockProjectServiceImpl implements MockProjectService {

    private MockProjectRepository mockProjectRepository;

    private HashMapConverter hashMapConverter;

    private DataRepository dataRepository;


    public MockProjectServiceImpl(MockProjectRepository mockProjectRepository, HashMapConverter hashMapConverter, DataRepository dataRepository) {
        this.mockProjectRepository = mockProjectRepository;
        this.hashMapConverter = hashMapConverter;
        this.dataRepository = dataRepository;
    }

    @Override
    public String createMockProject(MockDTO mockDTO) {
        MockProject mockProject = mockProjectRepository.findByMockName(mockDTO.getMockName());

        if (mockProject != null) {

            if (!Objects.equals(mockProject.getDataSchemaJson(), hashMapConverter.convertToDatabaseColumn(mockDTO.getDataSchema()))) {
                List<Data> dataEntries = mockProject.getDataEntries();

                mockProject.setDataEntries(null);

                dataRepository.deleteAll(dataEntries);

                saveData(mockDTO, mockProject);
            }

        } else {
            mockProject = new MockProject();
            mockProject.setMockName(mockDTO.getMockName());

            mockProject.setDataSchemaJson(hashMapConverter.convertToDatabaseColumn(mockDTO.getDataSchema()));

            saveData(mockDTO, mockProject);
        }

        EndPoint endPoint = new EndPoint();
        EndPointDTO endPointDTO = mockDTO.getEndPoint();
        endPoint.setUrl(endPointDTO.getUrl());
        endPoint.setOperation(endPointDTO.getOperation());
        endPoint.setMockProject(mockProject);


        Response response = new Response();
        ResponseDTO responseDTO = endPointDTO.getResponse();
        response.setRid(responseDTO.getRid());
        response.setResponseJson(hashMapConverter.convertToDatabaseColumn(responseDTO.getResponse()));
        response.setEndPoint(endPoint);


        List<Response> responses = new ArrayList<>();
        responses.add(response);
        endPoint.setResponses(responses);


        List<EndPoint> endPoints = new ArrayList<>();
        endPoints.add(endPoint);
        mockProject.setEndpoints(endPoints);


        mockProjectRepository.save(mockProject);

        return "Successfully created a mockApi project";
    }

    private void saveData(MockDTO mockDTO, MockProject mockProject) {
        List<Data> dataList = new ArrayList<>();
        long i = 1L;
        for (;i <= 50L; i++) {
            Data data = new Data();
            Map<String, Object> dataEntry = new HashMap<>();

            for (Map.Entry<String, Object> entry : mockDTO.getDataSchema().entrySet()) {
                if (Objects.equals(entry.getValue().toString(), "String")) {
                    dataEntry.put(entry.getKey(), entry.getKey() + i);
                } else if (Objects.equals(entry.getValue().toString(), "Number")) {
                    dataEntry.put(entry.getKey(), i + 10L);
                } else if (Objects.equals(entry.getValue().toString(), "Boolean")) {
                    dataEntry.put(entry.getKey(), false);
                } else if (Objects.equals(entry.getValue().toString(), "Array")) {
                    dataEntry.put(entry.getKey(), new Object[]{});
                } else if (Objects.equals(entry.getValue().toString(), "Object")) {
                    dataEntry.put(entry.getKey(), new Object());
                }
            }

            data.setDataEntry(dataEntry);
            data.setDataJson(hashMapConverter.convertToDatabaseColumn(dataEntry));
            data.setMockProject(mockProject);

            dataList.add(data);
        }
        mockProject.setDataEntries(dataList);
    }
}
