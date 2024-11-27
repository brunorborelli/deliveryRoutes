package com.engsoft.marmita.service;

import com.engsoft.marmita.exceptions.ObjetoNaoEncontradoException;
import com.engsoft.marmita.model.Marmita;
import com.engsoft.marmita.repository.MarmitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarmitaService {

    private final MarmitaRepository marmitaRepository;

    public List<Marmita> getAll() {
        return marmitaRepository.findAll();
    }

    public Marmita getById(Long id) {
        return marmitaRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi encontrada uma marmita com o ID " + id));
    }

}
