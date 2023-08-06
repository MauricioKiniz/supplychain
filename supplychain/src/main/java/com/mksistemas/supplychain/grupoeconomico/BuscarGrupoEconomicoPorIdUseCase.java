package com.mksistemas.supplychain.grupoeconomico;

import java.util.List;
import java.util.Optional;

import com.mksistemas.supplychain.library.processes.UseCase;

public interface BuscarGrupoEconomicoPorIdUseCase
		extends
		UseCase<BuscarGrupoEconomicoPorIdUseCase.Requisicao, Optional<BuscarGrupoEconomicoPorIdUseCase.GrupoEconomicoDto>> {
	record Requisicao(String grupoEconomicoId) {
	}

	record GrupoEconomicoDto(String grupoEconomicoId, String nome, boolean ativo, List<String> organizacoes) {
	}
}
