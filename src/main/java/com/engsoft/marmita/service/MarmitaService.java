package com.engsoft.marmita.service;

import com.engsoft.marmita.exceptions.ObjetoNaoEncontradoException;
import com.engsoft.marmita.model.Marmita;
import com.engsoft.marmita.repository.MarmitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarmitaService {

    private final MarmitaRepository marmitaRepository;

    @Autowired
    public MarmitaService(MarmitaRepository marmitaRepository) {
        this.marmitaRepository = marmitaRepository;
    }

    public List<Marmita> listarTodas() {
        return marmitaRepository.findAll();
    }
    public Marmita buscarPorId(Long id) {
        return marmitaRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Marmita não encontrada!"));
    }
}
