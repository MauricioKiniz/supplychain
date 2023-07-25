package com.mksistemas.supplychain.grupoeconomico.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events.GrupoEconomicoDesativadoEvent;
import com.mksistemas.supplychain.grupoeconomico.application.desativar.DesativarGrupoEconomicoReporter;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class DesativarGrupoEconomicoReporterImpl
		implements DesativarGrupoEconomicoReporter, BaseReporterEvent<GrupoEconomicoDesativadoEvent, GrupoEconomico> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public DesativarGrupoEconomicoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	@Override
	public void reportEvent(Result<Void, GrupoEconomico> eventToReport) {
		publicarEmCasoSucesso(eventToReport, grupo -> GrupoEconomicoDesativadoEvent.builder().withAtivo(grupo.isAtivo())
				.withGrupoEconomicoId(grupo.getGrupoEconomicoId().toLowerCase()).withNome(grupo.getNome()).build(),
				applicationEventPublisher);
	}

}
