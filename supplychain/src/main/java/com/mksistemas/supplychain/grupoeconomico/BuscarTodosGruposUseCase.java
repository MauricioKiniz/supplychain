package com.mksistemas.supplychain.grupoeconomico;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mksistemas.supplychain.library.converters.LongToTsidStringConverter;
import com.mksistemas.supplychain.library.processes.UseCase;

public interface BuscarTodosGruposUseCase extends UseCase<Void, List<BuscarTodosGruposUseCase.GrupoEconomicoDto>> {

	record GrupoEconomicoDto(@JsonDeserialize(converter = LongToTsidStringConverter.class) String grupoEconomicoId,
			String nome,
			String ativo, int totalOrganizacoesVinculadas) {
	}

}
