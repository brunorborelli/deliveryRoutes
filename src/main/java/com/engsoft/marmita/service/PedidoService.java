package com.engsoft.marmita.service;

import com.engsoft.marmita.exceptions.NegocioException;
import com.engsoft.marmita.exceptions.ObjetoNaoEncontradoException;
import com.engsoft.marmita.mapper.PedidoMapper;
import com.engsoft.marmita.model.Pedido;
import com.engsoft.marmita.model.dto.PedidoDTO;
import com.engsoft.marmita.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido não encontrado com o id: " + id));
    }

    public Pedido registrarPedido(PedidoDTO dto){
        validarLocalizacao(dto);
        validarCamposObrigatorios(dto);
        return pedidoRepository.save(PedidoMapper.toEntity(dto));
    }

    private void validarCamposObrigatorios(PedidoDTO dto) {
        if (dto.getNomeCliente() == null || dto.getNomeCliente().isEmpty()) {
            throw new NegocioException("Nome do cliente é obrigatório.");
        }
        if (dto.getCpf() == null || dto.getCpf().isEmpty()) {
            throw new NegocioException("CPF é obrigatório.");
        }
        if (dto.getRua() == null || dto.getRua().isEmpty()) {
            throw new NegocioException("Rua é obrigatória.");
        }
        if (dto.getNumero() == null || dto.getNumero().isEmpty()) {
            throw new NegocioException("Número do endereço é obrigatório.");
        }
        if (dto.getCep() == null || dto.getCep().isEmpty()) {
            throw new NegocioException("CEP é obrigatório.");
        }
        if (dto.getCidade() == null || dto.getCidade().isEmpty()) {
            throw new NegocioException("Cidade é obrigatória.");
        }
        if (dto.getEstado() == null || dto.getEstado().isEmpty()) {
            throw new NegocioException("Estado é obrigatório.");
        }
        if (dto.getPais() == null || dto.getPais().isEmpty()) {
            throw new NegocioException("País é obrigatório.");
        }
        if (dto.getPagamento() == null || dto.getPagamento().isEmpty()) {
            throw new NegocioException("Forma de pagamento é obrigatória.");
        }
        if (dto.getMarmitaId() == null) {
            throw new NegocioException("O tipo de Marmita é obrigatória.");
        }
    }

    private static final List<String> CIDADES_REGIAO_METROPOLITANA = Arrays.asList(
            "Goiânia", "Aparecida de Goiânia", "Anápolis", "Senador Canedo",
            "Trindade", "Goianira", "Ceres", "Nerópolis", "Fazenda Nova"
    );

    private void validarLocalizacao(PedidoDTO dto) {
        if (!CIDADES_REGIAO_METROPOLITANA.contains(dto.getCidade())) {
            throw new NegocioException("A cidade deve ser uma da Região Metropolitana de Goiânia ou Goiânia.");
        }
        if (!"GO".equalsIgnoreCase(dto.getEstado())) {
            throw new NegocioException("O estado deve ser GO.");
        }
        if (!"Brasil".equalsIgnoreCase(dto.getPais())) {
            throw new NegocioException("O país deve ser Brasil.");
        }
    }

}
