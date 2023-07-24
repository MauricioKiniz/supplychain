package com.mksistemas.supplychain.grupoeconomico;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.library.validators.tsid.TsidId;

public interface AlterarGrupoEconomicoUseCase
		extends UseCase<AlterarGrupoEconomicoUseCase.Requisicao, AlterarGrupoEconomicoUseCase.Resposta> {
	record Requisicao(@TsidId(message = GrupoEconomicoMessages.GRUPO_ID_INCORRETO) String grupoEconomicoId,
			String nome)
	{
	}

	record Resposta(String grupoEconomicoId) {
	}
}
