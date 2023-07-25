package com.mksistemas.supplychain.grupoeconomico;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.processes.UseCase;

import jakarta.validation.constraints.NotNull;

public interface DesativarGrupoEconomicoUseCase
		extends UseCase<DesativarGrupoEconomicoUseCase.Requisicao, DesativarGrupoEconomicoUseCase.Resposta> {

	record Requisicao(@NotNull(message = GrupoEconomicoMessages.GRUPO_ID_NULL) GrupoEconomicoId grupoEconomicoId) {
	}

	record Resposta(String grupoEconomicoId) {
	}
	
}
