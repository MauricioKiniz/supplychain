package com.mksistemas.supplychain.grupoeconomico.application.desvincularorganizacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.grupoeconomico.DesvincularOrganizacaoUseCase;
import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.application.desvincularorganizacao.DesvincularOrganizacaoReporter.ResultadoProcessoDesvinculo;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.organizacao.OrganizacaoFacade;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class DesvincularOrganizacaoService implements DesvincularOrganizacaoUseCase {

	private final OrganizacaoFacade organizacaoFacade;
	private final DesvincularOrganizacaoRepository repository;
	private final DesvincularOrganizacaoReporter reporter;

	public DesvincularOrganizacaoService(OrganizacaoFacade organizacaoFacade,
			DesvincularOrganizacaoRepository repository, DesvincularOrganizacaoReporter reporter) {
		this.organizacaoFacade = organizacaoFacade;
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		GrupoEconomicoId grupoId = GrupoEconomicoId.from(requisicao.grupoEconomicoId());
		GrupoEconomico grupo = repository.buscarPorId(grupoId)
				.orElseThrow(() -> new BusinessException(GrupoEconomicoMessages.GRUPO_NAO_ENCONTRADO));
		List<OrganizacaoProcessada> processadas = new ArrayList<>();
		requisicao.organizacoes().forEach(organizacaoId -> processarDesvinculacao(organizacaoId, grupo, processadas));
		List<String> removiveis = processadas.stream().filter(item -> item.erro() == null)
				.map(item -> item.organizacaoId()).toList();
		if (Boolean.FALSE.equals(removiveis.isEmpty())) {
			grupo.getOrganizacoes().removeAll(removiveis);
			repository.salvar(grupo);
			reporter.reportEvent(Result.ofSuccess(new ResultadoProcessoDesvinculo(grupo, processadas)));
		}
		return new Resposta(processadas);
	}

	private void processarDesvinculacao(String organizacaoId, GrupoEconomico grupo,
			List<OrganizacaoProcessada> processadas) {
		if (grupo.organizacaoVinculada(organizacaoId)) {
			processadas.add(new OrganizacaoProcessada(organizacaoId, null));
		} else {
			processadas
					.add(new OrganizacaoProcessada(organizacaoId, GrupoEconomicoMessages.ORGANIZACAO_NAO_ENCONTRADA));
		}
	}


}
