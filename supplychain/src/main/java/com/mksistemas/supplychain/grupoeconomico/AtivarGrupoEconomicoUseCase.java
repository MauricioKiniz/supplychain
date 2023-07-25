package com.mksistemas.supplychain.grupoeconomico;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.processes.UseCase;

import jakarta.validation.constraints.NotNull;

public interface AtivarGrupoEconomicoUseCase
		extends UseCase<AtivarGrupoEconomicoUseCase.Requisicao, AtivarGrupoEconomicoUseCase.Resposta> {
	record Requisicao(@NotNull(message = GrupoEconomicoMessages.GRUPO_ID_NULL) GrupoEconomicoId grupoEconomicoId)
	{
	}

	record Resposta(String grupoEconomicoId) {
	}
}