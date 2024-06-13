package com.example.PD_server.controller;

import com.example.PD_server.dto.RequestDto;
import com.example.PD_server.model.Request;
import com.example.PD_server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
        Request request = requestService.createRequest(requestDto);
        return ResponseEntity.ok(requestService.toDto(request));
    }

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        List<RequestDto> requestDtos = requests.stream().map(requestService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(requestDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequestById(@PathVariable Long id) {
        Request request = requestService.getRequestById(id);
        return ResponseEntity.ok(requestService.toDto(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestDto> updateRequest(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        Request request = requestService.updateRequest(id, requestDto);
        return ResponseEntity.ok(requestService.toDto(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}