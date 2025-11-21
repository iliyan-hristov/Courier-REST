package com.example.Project_api.web;

import com.example.Project_api.service.StatisticsService;
import com.example.Project_api.web.dto.OrderEventRequest;
import com.example.Project_api.web.dto.StatisticsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    private final StatisticsService statisticsService;

    public StatisticController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping("/events")
    public void ingestEvent(@Valid @RequestBody OrderEventRequest request) {
        statisticsService.saveOrderEvent(request);
    }

    @GetMapping
    public StatisticsResponse getStatistics() {
        return statisticsService.computeStats();
    }

}
