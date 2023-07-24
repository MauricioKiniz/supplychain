package com.mksistemas.supplychain.organizacao.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;
import com.mksistemas.supplychain.organizacao.adapter.reporter.events.OrganizacaoDesativadaEvent;
import com.mksistemas.supplychain.organizacao.application.desativar.DesativarOrganizacaoReporter;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;

@Component
class DesativarOrganizacaoReporterImpl
		implements DesativarOrganizacaoReporter, BaseReporterEvent<OrganizacaoDesativadaEvent, Organizacao> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public DesativarOrganizacaoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, Organizacao> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				organizacao -> OrganizacaoDesativadaEvent.builder().withAtivo(organizacao.isAtivo())
						.withCodigoPais(organizacao.getCodigoPais()).withIdentidade(organizacao.getIdentidade())
						.withIdExterno(organizacao.getIdentidade()).withNome(organizacao.getNome())
						.withOrganizacaoId(organizacao.getOrganizacaoId().toLowerCase())
						.withTimeZoneInSeconds(organizacao.getTimeZoneInSeconds()).build(),
				applicationEventPublisher);
	}

}
