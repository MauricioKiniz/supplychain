package com.mksistemas.supplychain.organizacao.application.alterar;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.organizacao.AlterarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.OrganizacaoMessages;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class AlterarOrganizacaoService implements AlterarOrganizacaoUseCase {

	private final AlterarOrganizacaoRepository repository;
	private final AlterarOrganizacaoReporter reporter;

	public AlterarOrganizacaoService(final AlterarOrganizacaoRepository repository,
			final AlterarOrganizacaoReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		verificarTimeZone(requisicao);
		verificarOrganizacaoExistente(requisicao);
		Organizacao organizacao = salvarOrganizacao(requisicao);
		reporter.reportEvent(Result.ofSuccess(organizacao));
		return new Resposta(organizacao.getOrganizacaoId().toLowerCase());
	}

	private void verificarTimeZone(Requisicao requisicao) {
		try {
			ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(requisicao.timeZoneInSeconds()));
		} catch (DateTimeException exception) {
			throw new BusinessException(OrganizacaoMessages.TIMEZONE_INCORRETO);
		}
	}

	private void verificarOrganizacaoExistente(Requisicao requisicao) {
		if (repository.organizacaoExiste(OrganizacaoId.from(requisicao.organizacaoId()), requisicao.idExterno())) {
			throw new BusinessException(OrganizacaoMessages.ORGANIZACAO_JAEXISTE);
		}
	}

	private Organizacao salvarOrganizacao(Requisicao requisicao) {
		Organizacao organizacao = buscarOrganizacao(requisicao);
		repository.salvar(organizacao);
		reporter.reportEvent(Result.ofSuccess(organizacao));
		return organizacao;
	}

	private Organizacao buscarOrganizacao(Requisicao requisicao) {
		Organizacao organizacao = repository.buscarOrganizacaoPorId(OrganizacaoId.from(requisicao.organizacaoId()))
				.orElseThrow(() -> new BusinessException(OrganizacaoMessages.ORGANIZACAO_NAO_ENCONTRADA));
		organizacao.setCodigoPais(requisicao.codigoPais());
		organizacao.setIdentidade(requisicao.identidade());
		organizacao.setIdExterno(requisicao.idExterno());
		organizacao.setNome(requisicao.nome());
		organizacao.setTimeZoneInSeconds(requisicao.timeZoneInSeconds());
		return organizacao;
	}

}
