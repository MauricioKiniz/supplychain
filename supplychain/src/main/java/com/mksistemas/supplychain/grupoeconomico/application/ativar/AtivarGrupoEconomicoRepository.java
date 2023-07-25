package com.mksistemas.supplychain.grupoeconomico.application.ativar;

import java.util.Optional;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;

public interface AtivarGrupoEconomicoRepository {
	Optional<GrupoEconomico> buscarPorId(GrupoEconomicoId grupoEconomicoId);

	void salvar(GrupoEconomico grupo);
}
