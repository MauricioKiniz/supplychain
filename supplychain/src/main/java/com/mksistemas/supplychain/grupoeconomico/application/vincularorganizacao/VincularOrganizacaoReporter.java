package com.mksistemas.supplychain.grupoeconomico.application.vincularorganizacao;

import java.util.List;

import com.mksistemas.supplychain.grupoeconomico.VincularOrganizacaoUseCase.OrganizacaoProcessada;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.processes.Reporter;

public interface VincularOrganizacaoReporter
		extends Reporter<Void, VincularOrganizacaoReporter.ResultadoProcessoVinculo> {

	record ResultadoProcessoVinculo(GrupoEconomico grupoEconomico, List<OrganizacaoProcessada> processadas) {
	}

}
