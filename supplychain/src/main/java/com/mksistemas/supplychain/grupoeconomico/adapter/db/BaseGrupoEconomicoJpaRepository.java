package com.mksistemas.supplychain.grupoeconomico.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;

public interface BaseGrupoEconomicoJpaRepository extends JpaRepository<GrupoEconomico, GrupoEconomicoId> {
}
