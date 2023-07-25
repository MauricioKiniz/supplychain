package com.mksistemas.supplychain.grupoeconomico;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.processes.UseCase;

public interface AtivarGrupoEconomicoUseCase
		extends UseCase<AtivarGrupoEconomicoUseCase.Requisicao, AtivarGrupoEconomicoUseCase.Resposta> {
	record Requisicao(GrupoEconomicoId grupoEconomicoId) {
	}

	record Resposta(String grupoEconomicoId) {
	}
}
