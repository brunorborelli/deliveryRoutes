package com.engsoft.marmita.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeCliente;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(length = 255)
    private String enderecoCompleto;

    @Column(nullable = false, length = 150)
    private String rua;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 255)
    private String referencia;

    @Column(nullable = false, length = 20)
    private String cep;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 50)
    private String pais;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "marmita_id")
    private Marmita marmita;

    @Column(length = 500)
    private String obs;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Pagamento pagamento;

    private Double troco;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false)
    private LocalDateTime datahora;

    @Column(nullable = false)
    private Integer quantidade;
}
