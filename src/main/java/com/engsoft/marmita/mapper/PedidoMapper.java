package com.engsoft.marmita.mapper;

import com.engsoft.marmita.model.Pagamento;
import com.engsoft.marmita.model.Pedido;
import com.engsoft.marmita.model.dto.PedidoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PedidoMapper {
    public static PedidoDTO toDTO(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getCpf(),
                pedido.getTelefone(),
                pedido.getEnderecoCompleto(),
                pedido.getRua(),
                pedido.getNumero(),
                pedido.getComplemento(),
                pedido.getReferencia(),
                pedido.getCep(),
                pedido.getCidade(),
                pedido.getEstado(),
                pedido.getPais(),
                pedido.getEndereco() != null ? pedido.getEndereco().getId() : null,
                pedido.getMarmita() != null ? pedido.getMarmita().getId() : null,
                pedido.getObs(),
                pedido.getPagamento() != null ? pedido.getPagamento().name() : null,
                pedido.getTroco(),
                pedido.getValorTotal(),
                pedido.getQuantidade(),
                pedido.getLatitude(),
                pedido.getLongitude()
        );
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setNomeCliente(pedidoDTO.getNomeCliente());
        pedido.setCpf(pedidoDTO.getCpf());
        pedido.setTelefone(pedidoDTO.getTelefone());
        pedido.setEnderecoCompleto(pedidoDTO.getEnderecoCompleto());
        pedido.setRua(pedidoDTO.getRua());
        pedido.setNumero(pedidoDTO.getNumero());
        pedido.setComplemento(pedidoDTO.getComplemento());
        pedido.setReferencia(pedidoDTO.getReferencia());
        pedido.setCep(pedidoDTO.getCep());
        pedido.setCidade(pedidoDTO.getCidade());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setPais(pedidoDTO.getPais());
        pedido.setObs(pedidoDTO.getObs());
        if (pedidoDTO.getPagamento() != null) {
            pedido.setPagamento(Pagamento.valueOf(pedidoDTO.getPagamento()));
        }
        pedido.setTroco(pedidoDTO.getTroco());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setDatahora(LocalDateTime.now());
        pedido.setQuantidade(pedidoDTO.getQuantidade());
        pedido.setLatitude(pedidoDTO.getLatitude());
        pedido.setLongitude(pedidoDTO.getLongitude());
        return pedido;
    }
}