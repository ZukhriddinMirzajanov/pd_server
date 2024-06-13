package com.example.PD_server.service;

import com.example.PD_server.dto.RequestDto;
import com.example.PD_server.exception.ResourceNotFoundException;
import com.example.PD_server.model.Region;
import com.example.PD_server.model.Request;
import com.example.PD_server.repository.RegionRepository;
import com.example.PD_server.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RegionRepository regionRepository;

    public Request createRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setDescription(requestDto.getDescription());
        Region region = regionRepository.findById(requestDto.getRegionId()).orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        request.setRegion(region);
        return requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
    }

    public Request updateRequest(Long id, RequestDto requestDto) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setDescription(requestDto.getDescription());
        Region region = regionRepository.findById(requestDto.getRegionId()).orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        request.setRegion(region);
        return requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public RequestDto toDto(Request request) {
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setDescription(request.getDescription());
        requestDto.setRegionId(request.getRegion().getId());
        return requestDto;
    }
}
