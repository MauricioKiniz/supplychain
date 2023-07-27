package com.mksistemas.supplychain.grupoeconomico.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events.GrupoEconomicoOrganizacaoVinculadaEvent;
import com.mksistemas.supplychain.grupoeconomico.application.vincularorganizacao.VincularOrganizacaoReporter;
import com.mksistemas.supplychain.grupoeconomico.application.vincularorganizacao.VincularOrganizacaoReporter.ResultadoProcessoVinculo;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class VincularOrganizacaoReporterImpl implements VincularOrganizacaoReporter,
		BaseReporterEvent<GrupoEconomicoOrganizacaoVinculadaEvent, ResultadoProcessoVinculo> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public VincularOrganizacaoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, ResultadoProcessoVinculo> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				vinculado -> GrupoEconomicoOrganizacaoVinculadaEvent.builder()
						.withGrupoEconomicoId(vinculado.grupoEconomico().getGrupoEconomicoId().toLowerCase())
						.withOrganizacoesVinculadas(vinculado.processadas().stream().filter(item -> item.erro() == null)
								.map(item -> item.organizacaoId()).toList())
						.build(),
				applicationEventPublisher);
	}

}
