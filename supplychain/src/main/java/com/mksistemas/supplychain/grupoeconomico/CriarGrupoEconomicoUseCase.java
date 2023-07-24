package com.mksistemas.supplychain.grupoeconomico;

import com.mksistemas.supplychain.library.processes.UseCase;

import jakarta.validation.constraints.NotBlank;

public interface CriarGrupoEconomicoUseCase
		extends UseCase<CriarGrupoEconomicoUseCase.Requisicao, CriarGrupoEconomicoUseCase.Resposta> {

	record Requisicao(@NotBlank(message = GrupoEconomicoMessages.NOME_BLANK) String nome) {
	}

	record Resposta(String grupoEconomicoId) {
	}

}
