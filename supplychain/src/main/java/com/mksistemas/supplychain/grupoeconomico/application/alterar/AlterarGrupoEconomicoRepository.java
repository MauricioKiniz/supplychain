package com.mksistemas.supplychain.grupoeconomico.application.alterar;

import java.util.Optional;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;

public interface AlterarGrupoEconomicoRepository {
	Optional<GrupoEconomico> buscarPorId(GrupoEconomicoId grupoEconomicoId);

	void salvar(GrupoEconomico grupo);
}
