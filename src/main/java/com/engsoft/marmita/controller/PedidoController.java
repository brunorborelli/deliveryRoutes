package com.engsoft.marmita.controller;

import com.engsoft.marmita.model.Pedido;
import com.engsoft.marmita.model.dto.PedidoDTO;
import com.engsoft.marmita.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> registrarPedido(@RequestBody PedidoDTO dto) {
            Pedido pedidoRegistrado = pedidoService.registrarPedido(dto);
            return new ResponseEntity<>(pedidoRegistrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
            Pedido pedido = pedidoService.getPedidoById(id);
            return new ResponseEntity<>(pedido, HttpStatus.OK);

    }
}