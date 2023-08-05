package com.mksistemas.supplychain.grupoeconomico;

import java.util.List;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.library.validators.tsid.TsidId;

public interface BuscarGruposEconomicosPorOrganizacaoUseCase extends
		UseCase<BuscarGruposEconomicosPorOrganizacaoUseCase.Requisicao, BuscarGruposEconomicosPorOrganizacaoUseCase.Resposta> {
	record Requisicao(@TsidId(message = GrupoEconomicoMessages.ORGANIZACAO_ID_INCORRETA) String organizacaoId) {
	}

	record Resposta(List<String> gruposEconomicos) {
	}
}
