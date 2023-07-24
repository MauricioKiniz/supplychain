package com.mksistemas.supplychain.grupoeconomico.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events.GrupoEconomicoAlteradoEvent;
import com.mksistemas.supplychain.grupoeconomico.application.alterar.AlterarGrupoEconomicoReporter;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class AlterarGrupoEconomicoReporterImpl
		implements AlterarGrupoEconomicoReporter, BaseReporterEvent<GrupoEconomicoAlteradoEvent, GrupoEconomico> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public AlterarGrupoEconomicoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	@Override
	public void reportEvent(Result<Void, GrupoEconomico> eventToReport) {
		publicarEmCasoSucesso(eventToReport, grupo -> GrupoEconomicoAlteradoEvent.builder().withAtivo(grupo.isAtivo())
				.withGrupoEconomicoId(grupo.getGrupoEconomicoId().toLowerCase()).withNome(grupo.getNome()).build(),
				applicationEventPublisher);
	}

}
