package com.engsoft.marmita.service;

import com.engsoft.marmita.model.delivery.DeliveryPoint;
import com.engsoft.marmita.model.delivery.DeliveryRoute;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @InjectMocks
    DeliveryService deliveryService;

    @Test
    void calcularMelhorRota_DeveRetornarRotaComDistanciaCorreta() {
        // Stubs
        DeliveryPoint ponto1 = new DeliveryPoint("Cliente A", "Pedido 1", "Pagamento 1", "Obs 1", "Endereço A", -23.55052, -46.633308, null);
        DeliveryPoint ponto2 = new DeliveryPoint("Cliente B", "Pedido 2", "Pagamento 2", "Obs 2", "Endereço B", -23.551000, -46.634000, null);
        DeliveryPoint ponto3 = new DeliveryPoint("Cliente C", "Pedido 3", "Pagamento 3", "Obs 3", "Endereço C", -23.552000, -46.635000, null);

        List<DeliveryPoint> pontos = Arrays.asList(ponto1, ponto2, ponto3);

        DeliveryRoute rotaCalculada = deliveryService.calcularMelhorRota(pontos);

        assertNotNull(rotaCalculada);
        assertEquals(3, rotaCalculada.getPoints().size()); // Deve conter os 3 pontos

        // Verificar se o ponto inicial é o mesmo
        assertEquals(ponto1, rotaCalculada.getPoints().get(0));

        // Verificar se a distância total foi calculada
        assertTrue(rotaCalculada.getTotalDistance() > 0, "A distância total deve ser maior que zero");
    }
}