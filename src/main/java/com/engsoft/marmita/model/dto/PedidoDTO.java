package com.engsoft.marmita.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private String nomeCliente;
    private String cpf;
    private String enderecoCompleto;
    private String rua;
    private String numero;
    private String complemento;
    private String referencia;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private Long enderecoId;
    private Long marmitaId;
    private String obs;
    private String pagamento;
    private Double troco;
    private Double valorTotal;
    private Integer quantidade;
    private Double latitute;
    private Double Longitude;
}
