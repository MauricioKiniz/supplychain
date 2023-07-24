package com.mksistemas.supplychain.organizacao.application.desativar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.organizacao.DesativarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.OrganizacaoMessages;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class DesativarOrganizacaoService implements DesativarOrganizacaoUseCase {

	private final DesativarOrganizacaoRepository repository;
	private final DesativarOrganizacaoReporter reporter;

	public DesativarOrganizacaoService(DesativarOrganizacaoRepository repository,
			DesativarOrganizacaoReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		Organizacao organizacao = repository.buscarOrganizacaoPorId(requisicao.organizacaoId())
				.orElseThrow(() -> new BusinessException(OrganizacaoMessages.ORGANIZACAO_NAO_ENCONTRADA));
		organizacao.desativar();
		repository.salvar(organizacao);
		reporter.reportEvent(Result.ofSuccess(organizacao));
		return new Resposta(organizacao.getOrganizacaoId().toLowerCase());
	}

}
