package com.mksistemas.supplychain.grupoeconomico.adapter.consumer;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.BuscarGruposEconomicosPorOrganizacaoUseCase;
import com.mksistemas.supplychain.grupoeconomico.DesvincularOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.adapter.reporter.events.OrganizacaoDesativadaEvent;

@Component
class OrganizacaoEventsConsumer {

	private final DesvincularOrganizacaoUseCase desvincularOrganizacao;
	private final BuscarGruposEconomicosPorOrganizacaoUseCase buscarGruposPorOrganizacao;

	public OrganizacaoEventsConsumer(final DesvincularOrganizacaoUseCase desvincularOrganizacao,
			final BuscarGruposEconomicosPorOrganizacaoUseCase buscarGruposPorOrganizacao) {
		this.desvincularOrganizacao = desvincularOrganizacao;
		this.buscarGruposPorOrganizacao = buscarGruposPorOrganizacao;

	}

	@EventListener
	public void processarOrganizacaoDesativada(OrganizacaoDesativadaEvent evento) {
		BuscarGruposEconomicosPorOrganizacaoUseCase.Resposta listaGrupos = buscarGruposPorOrganizacao
				.execute(new BuscarGruposEconomicosPorOrganizacaoUseCase.Requisicao(evento.getOrganizacaoId()));
		listaGrupos.gruposEconomicos().forEach(grupoEconomicoId -> {
			desvincularOrganizacao.execute(
					new DesvincularOrganizacaoUseCase.Requisicao(grupoEconomicoId, List.of(evento.getOrganizacaoId())));
		});
	}
}
