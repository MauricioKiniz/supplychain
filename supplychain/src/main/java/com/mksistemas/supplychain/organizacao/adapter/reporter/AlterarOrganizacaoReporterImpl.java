package com.mksistemas.supplychain.organizacao.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;
import com.mksistemas.supplychain.organizacao.adapter.reporter.events.OrganizacaoAlteradaEvent;
import com.mksistemas.supplychain.organizacao.application.alterar.AlterarOrganizacaoReporter;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;

@Component
class AlterarOrganizacaoReporterImpl
		implements AlterarOrganizacaoReporter, BaseReporterEvent<OrganizacaoAlteradaEvent, Organizacao> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public AlterarOrganizacaoReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, Organizacao> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				organizacao -> OrganizacaoAlteradaEvent.builder().withAtivo(organizacao.isAtivo())
						.withCodigoPais(organizacao.getCodigoPais()).withIdentidade(organizacao.getIdentidade())
						.withIdExterno(organizacao.getIdentidade()).withNome(organizacao.getNome())
						.withOrganizacaoId(organizacao.getOrganizacaoId().toLowerCase())
						.withTimeZoneInSeconds(organizacao.getTimeZoneInSeconds()).build(),
				applicationEventPublisher);
	}

}
