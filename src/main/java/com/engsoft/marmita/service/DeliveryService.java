package com.engsoft.marmita.service;

import com.engsoft.marmita.model.delivery.DeliveryPoint;
import com.engsoft.marmita.model.delivery.DeliveryRoute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeliveryService {

//    private static final int EARTH_RADIUS_KM = 6371;

//    // Algoritmo do caixeiro viajante para calcular a melhor rota
//    public DeliveryRoute calculateBestRoute(List<DeliveryPoint> points) {
//        // IMPORTANTE: O ponto de partida é o primeiro da lista
//        DeliveryPoint startingPoint = points.get(0);
//
//        List<DeliveryPoint> bestRoute = new ArrayList<>();
//        double bestDistance = Double.MAX_VALUE;
//
//        List<DeliveryPoint> remainingPoints = new ArrayList<>(points.subList(1, points.size()));
//        List<List<DeliveryPoint>> permutations = generatePermutations(remainingPoints);
//
//        for (List<DeliveryPoint> route : permutations) {
//            route.add(0, startingPoint);
//            double currentDistance = calculateTotalDistance(route);
//            if (currentDistance < bestDistance) {
//                bestDistance = currentDistance;
//                bestRoute = new ArrayList<>(route);
//            }
//        }
//
//        return new DeliveryRoute(bestRoute, bestDistance);
//    }
//
//    private double calculateTotalDistance(List<DeliveryPoint> route) {
//        double distance = 0;
//        for (int i = 0; i < route.size() - 1; i++) {
//            DeliveryPoint from = route.get(i);
//            DeliveryPoint to = route.get(i + 1);
//            distance += calculateDistance(
//                    from.getLatitude(), from.getLongitude(),
//                    to.getLatitude(), to.getLongitude());
//        }
//        return distance;
//    }
//
//    // Fórmula de Haversine para calcular distancias entre latitudes
//    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//        double latDistance = Math.toRadians(lat2 - lat1);
//        double lonDistance = Math.toRadians(lon2 - lon1);
//
//        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return EARTH_RADIUS_KM * c;
//    }
//
//    // Gera todas as permutações possíveis - FORÇA BRUTA
//    private List<List<DeliveryPoint>> generatePermutations(List<DeliveryPoint> points) {
//        List<List<DeliveryPoint>> permutations = new ArrayList<>();
//        permute(points, 0, permutations);
//        return permutations;
//    }
//
//    private void permute(List<DeliveryPoint> points, int index, List<List<DeliveryPoint>> permutations) {
//        if (index == points.size() - 1) {
//            permutations.add(new ArrayList<>(points));
//            return;
//        }
//
//        for (int i = index; i < points.size(); i++) {
//            Collections.swap(points, i, index);
//            permute(points, index + 1, permutations);
//            Collections.swap(points, i, index);
//        }
//    }
//
//    public void generateGoogleMapsLinks(List<DeliveryPoint> points) {
//        for (int i = 1; i < points.size(); i++) {
//            DeliveryPoint point = points.get(i);
//            StringBuilder message = new StringBuilder();
//            message
//                    .append("https://www.google.com/maps?q=")
//                    .append(point.getLatitude()).append(",").append(point.getLongitude()).append("");
//            point.setRotaMsg(String.valueOf(message));
//        }
//    }

    // Programação dinamica


    private static final int RAIO_TERRA_KM = 6371;

    public DeliveryRoute calcularMelhorRota(List<DeliveryPoint> pontos) {
        // Ponto de partida é o primeiro da lista
        DeliveryPoint pontoInicial = pontos.get(0);
        List<DeliveryPoint> pontosEntrega = pontos.subList(1, pontos.size());

        // Número de pontos (excluindo o ponto inicial)
        int n = pontosEntrega.size();

        // Matriz de memoização
        double[][] memo = new double[n][1 << n];

        // Preenche a matriz com um valor indicando "não calculado"
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                memo[i][j] = -1;
            }
        }

        // Calcula a menor distância a partir do ponto inicial
        double menorDistancia = encontrarMelhorRota(pontoInicial, pontosEntrega, 0, 1, memo);

        // Reconstrói a melhor rota
        List<DeliveryPoint> melhorRota = reconstruirRota(pontoInicial, pontosEntrega, memo);

        return new DeliveryRoute(melhorRota, menorDistancia);
    }

    private double encontrarMelhorRota(DeliveryPoint pontoInicial, List<DeliveryPoint> pontos, int atual, int visitados, double[][] memo) {
        int n = pontos.size();

        // Caso base: todos os pontos foram visitados
        if (visitados == (1 << n) - 1) {
            return calcularDistancia(
                    pontos.get(atual).getLatitude(),
                    pontos.get(atual).getLongitude(),
                    pontoInicial.getLatitude(),
                    pontoInicial.getLongitude()
            );
        }

        // Se já foi calculado, retorna o valor
        if (memo[atual][visitados] != -1) {
            return memo[atual][visitados];
        }

        double menorDistancia = Double.MAX_VALUE;

        // Tenta visitar os próximos pontos ainda não visitados
        for (int proximo = 0; proximo < n; proximo++) {
            if ((visitados & (1 << proximo)) == 0) { // Verifica se o ponto `proximo` não foi visitado
                double distancia = calcularDistancia(
                        pontos.get(atual).getLatitude(),
                        pontos.get(atual).getLongitude(),
                        pontos.get(proximo).getLatitude(),
                        pontos.get(proximo).getLongitude()
                );

                // Calcula a distância total ao visitar o ponto `proximo`
                double distanciaTotal = distancia + encontrarMelhorRota(pontoInicial, pontos, proximo, visitados | (1 << proximo), memo);

                menorDistancia = Math.min(menorDistancia, distanciaTotal);
            }
        }

        // Armazena no memo e retorna o valor
        memo[atual][visitados] = menorDistancia;
        return menorDistancia;
    }

    private List<DeliveryPoint> reconstruirRota(DeliveryPoint pontoInicial, List<DeliveryPoint> pontos, double[][] memo) {
        List<DeliveryPoint> rota = new ArrayList<>();
        rota.add(pontoInicial);

        int n = pontos.size();
        int visitados = 0;
        int atual = 0;

        while (visitados != (1 << n) - 1) {
            double menorDistancia = Double.MAX_VALUE;
            int proximoPonto = -1;

            // Busca o próximo ponto na melhor rota
            for (int proximo = 0; proximo < n; proximo++) {
                if ((visitados & (1 << proximo)) == 0) {
                    double distancia = calcularDistancia(
                            pontos.get(atual).getLatitude(),
                            pontos.get(atual).getLongitude(),
                            pontos.get(proximo).getLatitude(),
                            pontos.get(proximo).getLongitude()
                    ) + memo[proximo][visitados | (1 << proximo)];

                    if (distancia < menorDistancia) {
                        menorDistancia = distancia;
                        proximoPonto = proximo;
                    }
                }
            }

            // Adiciona o próximo ponto na rota
            if (proximoPonto != -1) {
                rota.add(pontos.get(proximoPonto));
                visitados |= (1 << proximoPonto);
                atual = proximoPonto;
            }
        }

        return rota;
    }

    // Fórmula de Haversine para calcular distâncias entre dois pontos (latitude/longitude)
    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double diferencaLat = Math.toRadians(lat2 - lat1);
        double diferencaLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(diferencaLat / 2) * Math.sin(diferencaLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(diferencaLon / 2) * Math.sin(diferencaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RAIO_TERRA_KM * c;
    }

    public void gerarLinksGoogleMaps(List<DeliveryPoint> pontos) {
        for (DeliveryPoint ponto : pontos) {
            String link = "https://www.google.com/maps?q=" + ponto.getLatitude() + "," + ponto.getLongitude();
            ponto.setRotaMsg(link);
        }
    }
}

