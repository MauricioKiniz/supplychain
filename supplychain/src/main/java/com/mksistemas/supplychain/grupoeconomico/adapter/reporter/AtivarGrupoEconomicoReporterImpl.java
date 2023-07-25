package com.mksistemas.supplychain.grupoeconomico.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events.GrupoEconomicoAtivadoEvent;
import com.mksistemas.supplychain.grupoeconomico.application.ativar.AtivarGrupoEconomicoReporter;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class AtivarGrupoEconomicoReporterImpl
		implements AtivarGrupoEconomicoReporter, BaseReporterEvent<GrupoEconomicoAtivadoEvent, GrupoEconomico> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public AtivarGrupoEconomicoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	@Override
	public void reportEvent(Result<Void, GrupoEconomico> eventToReport) {
		publicarEmCasoSucesso(eventToReport, grupo -> GrupoEconomicoAtivadoEvent.builder().withAtivo(grupo.isAtivo())
				.withGrupoEconomicoId(grupo.getGrupoEconomicoId().toLowerCase()).withNome(grupo.getNome()).build(),
				applicationEventPublisher);
	}

}
