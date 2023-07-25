package com.mksistemas.supplychain.grupoeconomico.application.desativar;

import java.util.Optional;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;

public interface DesativarGrupoEconomicoRepository {

	Optional<GrupoEconomico> buscarPorId(GrupoEconomicoId grupoEconomicoId);

	void salvar(GrupoEconomico grupo);

}
