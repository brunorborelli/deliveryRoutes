package com.engsoft.marmita.controller;

import com.engsoft.marmita.model.Marmita;
import com.engsoft.marmita.service.MarmitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marmitas")
public class MarmitaController {

    private final MarmitaService marmitaService;

    @Autowired
    public MarmitaController(MarmitaService marmitaService) {
        this.marmitaService = marmitaService;
    }

    @GetMapping
    public List<Marmita> listarTodas() {
        return marmitaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Marmita buscarPorId(@PathVariable Long id) {
        return marmitaService.buscarPorId(id);
    }
}
