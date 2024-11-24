package com.engsoft.marmita.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRoute {
    private List<DeliveryPoint> points;
    private double totalDistance;
}