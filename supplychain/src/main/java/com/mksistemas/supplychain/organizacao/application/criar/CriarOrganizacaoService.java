package com.mksistemas.supplychain.organizacao.application.criar;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.organizacao.CriarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.OrganizacaoMessages;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class CriarOrganizacaoService implements CriarOrganizacaoUseCase {

	private final CriarOrganizacaoRepository repository;
	private final CriarOrganizacaoReporter reporter;

	public CriarOrganizacaoService(final CriarOrganizacaoRepository repository, CriarOrganizacaoReporter reporter) {
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
		if (repository.organizacaoExiste(requisicao.idExterno())) {
			throw new BusinessException(OrganizacaoMessages.ORGANIZACAO_JAEXISTE);
		}
	}

	private Organizacao salvarOrganizacao(Requisicao requisicao) {
		Organizacao organizacao = Organizacao.builder().withCodigoPais(requisicao.codigoPais())
				.withIdExterno(requisicao.idExterno()).withIdentidade(requisicao.identidade())
				.withNome(requisicao.nome()).withOrganizacaoId(OrganizacaoId.generate())
				.withTimeZoneInSeconds(requisicao.timeZoneInSeconds()).build();
		repository.salvar(organizacao);
		return organizacao;
	}

}
