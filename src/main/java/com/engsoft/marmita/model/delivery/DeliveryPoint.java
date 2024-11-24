package com.engsoft.marmita.model.delivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPoint {
    private String cliente; // string com nome + cpf
    private String pedido; //Não é o obj é uma String concatenada com o pedido + quantidade + obs do pedido
    private String pagamento; // string concatenada do valor + forma de pagamento
    private String obs; //obs da entrega, diferente da obs do pedido
    private String address;
    @JsonIgnore
    private double latitude;
    @JsonIgnore
    private double longitude;
}