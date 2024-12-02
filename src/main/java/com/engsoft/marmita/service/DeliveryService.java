package com.engsoft.marmita.service;

import com.engsoft.marmita.model.delivery.DeliveryPoint;
import com.engsoft.marmita.model.delivery.DeliveryRoute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeliveryService {

    private static final int EARTH_RADIUS_KM = 6371;

    // Algoritmo do caixeiro viajante para calcular a melhor rota
    public DeliveryRoute calculateBestRoute(List<DeliveryPoint> points) {
        // IMPORTANTE: O ponto de partida é o primeiro da lista
        DeliveryPoint startingPoint = points.get(0);

        List<DeliveryPoint> bestRoute = new ArrayList<>();
        double bestDistance = Double.MAX_VALUE;

        List<DeliveryPoint> remainingPoints = new ArrayList<>(points.subList(1, points.size()));
        List<List<DeliveryPoint>> permutations = generatePermutations(remainingPoints);

        for (List<DeliveryPoint> route : permutations) {
            route.add(0, startingPoint);
            double currentDistance = calculateTotalDistance(route);
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                bestRoute = new ArrayList<>(route);
            }
        }

        return new DeliveryRoute(bestRoute, bestDistance);
    }

    private double calculateTotalDistance(List<DeliveryPoint> route) {
        double distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            DeliveryPoint from = route.get(i);
            DeliveryPoint to = route.get(i + 1);
            distance += calculateDistance(
                    from.getLatitude(), from.getLongitude(),
                    to.getLatitude(), to.getLongitude());
        }
        return distance;
    }

    // Fórmula de Haversine para calcular distancias entre latitudes
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    // Gera todas as permutações possíveis - FORÇA BRUTA
    private List<List<DeliveryPoint>> generatePermutations(List<DeliveryPoint> points) {
        List<List<DeliveryPoint>> permutations = new ArrayList<>();
        permute(points, 0, permutations);
        return permutations;
    }

    private void permute(List<DeliveryPoint> points, int index, List<List<DeliveryPoint>> permutations) {
        if (index == points.size() - 1) {
            permutations.add(new ArrayList<>(points));
            return;
        }

        for (int i = index; i < points.size(); i++) {
            Collections.swap(points, i, index);
            permute(points, index + 1, permutations);
            Collections.swap(points, i, index);
        }
    }

    public void generateGoogleMapsLinks(List<DeliveryPoint> points) {
        for (int i = 1; i < points.size(); i++) {
            DeliveryPoint point = points.get(i);
            StringBuilder message = new StringBuilder();
            message
                    .append("Link do Google Maps: https://www.google.com/maps?q=")
                    .append(point.getLatitude()).append(",").append(point.getLongitude()).append("\n");
            point.setRotaMsg(String.valueOf(message));
        }
    }

}
