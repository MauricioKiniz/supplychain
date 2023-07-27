package com.mksistemas.supplychain.grupoeconomico.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events.GrupoEconomicoOrganizacaoDesvinculadaEvent;
import com.mksistemas.supplychain.grupoeconomico.application.desvincularorganizacao.DesvincularOrganizacaoReporter;
import com.mksistemas.supplychain.grupoeconomico.application.desvincularorganizacao.DesvincularOrganizacaoReporter.ResultadoProcessoDesvinculo;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class DesvincularOrganizacaoReporterImpl implements DesvincularOrganizacaoReporter,
		BaseReporterEvent<GrupoEconomicoOrganizacaoDesvinculadaEvent, ResultadoProcessoDesvinculo> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public DesvincularOrganizacaoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, ResultadoProcessoDesvinculo> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				vinculado -> GrupoEconomicoOrganizacaoDesvinculadaEvent.builder()
						.withGrupoEconomicoId(vinculado.grupoEconomico().getGrupoEconomicoId().toLowerCase())
						.withOrganizacoesDesvinculadas(vinculado.processadas().stream()
								.filter(item -> item.erro() == null)
								.map(item -> item.organizacaoId()).toList())
						.build(),
				applicationEventPublisher);
	}

}
