package com.example.Project_api.service;

import com.example.Project_api.model.OrderEvent;
import com.example.Project_api.repository.OrderEventRepository;
import com.example.Project_api.web.dto.OrderEventRequest;
import com.example.Project_api.web.dto.StatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Value("${statistics.service.url}")
    private String statisticsServiceUrl;

    @Autowired
    private final OrderEventRepository orderEventRepository;

    public StatisticsService(OrderEventRepository repository) {
        this.orderEventRepository = repository;
    }

    public void saveOrderEvent(OrderEventRequest req) {
        OrderEvent e = new OrderEvent();
        e.setOrderId(req.getOrderId());
        e.setPrice(req.getPrice());
        e.setDeliveryTimeMinutes(req.getDeliveryTimeMinutes());
        orderEventRepository.save(e);
    }

    public StatisticsResponse computeStats() {
        List<OrderEvent> events = orderEventRepository.findAll();

        long totalOrders = events.size();
        double totalRevenue = events.stream().mapToDouble(OrderEvent::getPrice).sum();
        double avgOrderValue = totalOrders == 0 ? 0 : totalRevenue / totalOrders;
        double avgDeliveryTime = totalOrders == 0 ?
                0 :
                events.stream().mapToLong(OrderEvent::getDeliveryTimeMinutes).average().orElse(0);

        StatisticsResponse r = new StatisticsResponse();
        r.setTotalOrders(totalOrders);
        r.setTotalRevenue(totalRevenue);
        r.setAverageOrderValue(avgOrderValue);
        r.setAverageDeliveryTime(avgDeliveryTime);
        return r;
    }
}
