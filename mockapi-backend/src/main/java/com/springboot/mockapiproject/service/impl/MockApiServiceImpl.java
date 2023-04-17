package com.springboot.mockapiproject.service.impl;

import com.springboot.mockapiproject.entity.Data;
import com.springboot.mockapiproject.entity.EndPoint;
import com.springboot.mockapiproject.entity.MockProject;
import com.springboot.mockapiproject.entity.converter.HashMapConverter;
import com.springboot.mockapiproject.repository.DataRepository;
import com.springboot.mockapiproject.repository.EndPointRepository;
import com.springboot.mockapiproject.response.ApiResponse;
import com.springboot.mockapiproject.service.MockApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MockApiServiceImpl implements MockApiService {

    private EndPointRepository endPointRepository;

    private HashMapConverter hashMapConverter;

    private DataRepository dataRepository;

    public MockApiServiceImpl(EndPointRepository endPointRepository, HashMapConverter hashMapConverter, DataRepository dataRepository) {
        this.endPointRepository = endPointRepository;
        this.hashMapConverter = hashMapConverter;
        this.dataRepository = dataRepository;
    }

    @Override
    public List<Map<String, Object>> mockApiGetAll(String url) {

        EndPoint endPoint = endPointRepository.findEndPointByUrl(url);

        if (Objects.equals(endPoint.getOperation(), "GET")) {
            MockProject mockProject = endPoint.getMockProject();
            List<Data> dataList = mockProject.getDataEntries();

            List<Map<String, Object>> dataResponse = new ArrayList<>();
            for (Data data : dataList) {
                Map<String, Object> dataEntry = hashMapConverter.convertToEntityAttribute(data.getDataJson());
                dataEntry.put("id", data.getId());
                dataResponse.add(dataEntry);
            }

            return dataResponse;
        }

        return null;
    }

    @Override
    public Map<String, Object> mockApiGet(String url, Long id) {

        EndPoint endPoint = endPointRepository.findEndPointByUrl(url);

        if (Objects.equals(endPoint.getOperation(), "GET")) {
            Data data = dataRepository.findDataById(id);
            Map<String, Object> dataEntry = hashMapConverter.convertToEntityAttribute(data.getDataJson());
            dataEntry.put("id", data.getId());
            return dataEntry;
        }

        return null;
    }

    @Override
    public ApiResponse mockApiPost(String url, Map<String, Object> dataEntry) {

        EndPoint endPoint = endPointRepository.findEndPointByUrl(url);

        if (Objects.equals(endPoint.getOperation(), "POST")) {
            Data data = new Data();
            data.setMockProject(endPoint.getMockProject());
            data.setDataEntry(dataEntry);
            data.setDataJson(hashMapConverter.convertToDatabaseColumn(dataEntry));

            Data data1 = dataRepository.save(data);

            Map<String, Object> dataResponse = hashMapConverter.convertToEntityAttribute(data1.getDataJson());
            dataResponse.put("id", data1.getId());

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setResponse(hashMapConverter.convertToEntityAttribute(endPoint.getResponses().get(0).getResponseJson()));
            apiResponse.setData(dataResponse);

            return apiResponse;
        }


        return null;
    }
}
