package com.engsoft.marmita.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String pais;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 150)
    private String logradouro;

    @Column(length = 255)
    private String enderecoCompleto;

    private Double latitude;

    private Double longitude;

    @Column(nullable = false, length = 20)
    private String cep;

    @Column(length = 255)
    private String referencia;
}
