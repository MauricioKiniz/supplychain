package com.mksistemas.supplychain.grupoeconomico.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events.GrupoEconomicoCriadoEvent;
import com.mksistemas.supplychain.grupoeconomico.application.criar.CriarGrupoEconomicoReporter;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class CriarGrupoEconomicoReporterImpl
		implements BaseReporterEvent<GrupoEconomicoCriadoEvent, GrupoEconomico>, CriarGrupoEconomicoReporter {

	private final ApplicationEventPublisher applicationEventPublisher;

	public CriarGrupoEconomicoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, GrupoEconomico> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				grupoEconomico -> GrupoEconomicoCriadoEvent.builder()
						.withGrupoEconomicoId(grupoEconomico.getGrupoEconomicoId().toLowerCase())
						.withNome(grupoEconomico.getNome()).withAtivo(grupoEconomico.isAtivo()).build(),
				applicationEventPublisher);
	}

}
