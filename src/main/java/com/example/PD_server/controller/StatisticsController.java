package com.example.PD_server.controller;

import com.example.PD_server.dto.StatisticsDto;
import com.example.PD_server.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics() {
        StatisticsDto statisticsDto = statisticsService.getStatistics();
        return ResponseEntity.ok(statisticsDto);
    }
}
