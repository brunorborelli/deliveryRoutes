package com.engsoft.marmita.controller;

import com.engsoft.marmita.model.delivery.DeliveryPoint;
import com.engsoft.marmita.model.delivery.DeliveryRoute;
import com.engsoft.marmita.service.DeliveryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/route")
    public DeliveryRoute getRoute(@RequestBody List<DeliveryPoint> points) {
        DeliveryRoute route = deliveryService.calculateBestRoute(points);
        deliveryService.generateGoogleMapsLinks(route.getPoints());
        return route;
    }

    @PostMapping("/simulate")
    public DeliveryRoute simulateBestRoute() {
        List<DeliveryPoint> testPoints = new ArrayList<>();
        // o primeiro item do indice é sempre o ponto de partida
        testPoints.add(new DeliveryPoint(null, null, null, null,"Belo Horizonte", -19.916681, -43.934493,""));
        testPoints.add(new DeliveryPoint(null, null, null, null,"Rio de Janeiro", -22.906847, -43.172896,""));
        testPoints.add(new DeliveryPoint(null, null, null, null,"São Paulo", -23.55052, -46.633308,""));
        testPoints.add(new DeliveryPoint(null, null, null, null,"Brasília", -15.7801, -47.9292,""));
        testPoints.add(new DeliveryPoint(null, null, null, null,"Goiânia", -16.6868491, -49.2707899,""));

        return deliveryService.calculateBestRoute(testPoints);
    }
}
