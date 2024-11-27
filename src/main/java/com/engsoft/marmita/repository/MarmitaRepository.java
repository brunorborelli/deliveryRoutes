package com.engsoft.marmita.repository;

import com.engsoft.marmita.model.Marmita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarmitaRepository extends JpaRepository<Marmita, Long> {


}
