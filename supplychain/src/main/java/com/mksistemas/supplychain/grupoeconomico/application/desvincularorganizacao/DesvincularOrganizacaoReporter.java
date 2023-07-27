package com.mksistemas.supplychain.grupoeconomico.application.desvincularorganizacao;

import java.util.List;

import com.mksistemas.supplychain.grupoeconomico.DesvincularOrganizacaoUseCase;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.processes.Reporter;

public interface DesvincularOrganizacaoReporter
		extends Reporter<Void, DesvincularOrganizacaoReporter.ResultadoProcessoDesvinculo> {

	record ResultadoProcessoDesvinculo(GrupoEconomico grupoEconomico,
			List<DesvincularOrganizacaoUseCase.OrganizacaoProcessada> processadas) {
	}

}
