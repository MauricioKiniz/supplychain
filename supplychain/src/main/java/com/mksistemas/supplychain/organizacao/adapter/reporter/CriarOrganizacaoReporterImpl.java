package com.mksistemas.supplychain.organizacao.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;
import com.mksistemas.supplychain.organizacao.adapter.reporter.events.OrganizacaoCriadaEvent;
import com.mksistemas.supplychain.organizacao.application.criar.CriarOrganizacaoReporter;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;

@Component
class CriarOrganizacaoReporterImpl
		implements CriarOrganizacaoReporter, BaseReporterEvent<OrganizacaoCriadaEvent, Organizacao> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public CriarOrganizacaoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, Organizacao> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				organizacao -> OrganizacaoCriadaEvent.builder().withAtivo(organizacao.isAtivo())
						.withCodigoPais(organizacao.getCodigoPais()).withIdentidade(organizacao.getIdentidade())
						.withIdExterno(organizacao.getIdentidade()).withNome(organizacao.getNome())
						.withOrganizacaoId(organizacao.getOrganizacaoId().toLowerCase())
						.withTimeZoneInSeconds(organizacao.getTimeZoneInSeconds()).build(),
				applicationEventPublisher);
	}

}
