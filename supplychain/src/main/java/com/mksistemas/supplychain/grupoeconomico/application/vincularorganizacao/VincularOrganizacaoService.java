package com.mksistemas.supplychain.grupoeconomico.application.vincularorganizacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.VincularOrganizacaoUseCase;
import com.mksistemas.supplychain.grupoeconomico.application.vincularorganizacao.VincularOrganizacaoReporter.ResultadoProcessoVinculo;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.organizacao.OrganizacaoFacade;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class VincularOrganizacaoService implements VincularOrganizacaoUseCase {

	private final OrganizacaoFacade organizacaoFacade;
	private final VincularOrganizacaoRepository repository;
	private final VincularOrganizacaoReporter reporter;

	public VincularOrganizacaoService(OrganizacaoFacade organizacaoFacade, VincularOrganizacaoRepository repository,
			VincularOrganizacaoReporter reporter) {
		this.organizacaoFacade = organizacaoFacade;
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		GrupoEconomico grupo = repository.buscarPorId(requisicao.grupoEconomicoId())
				.orElseThrow(() -> new BusinessException(GrupoEconomicoMessages.GRUPO_NAO_ENCONTRADO));
		List<OrganizacaoProcessada> processadas = new ArrayList<>();
		requisicao.organizacoes().forEach(organizacaoId -> processarVinculacao(organizacaoId, grupo, processadas));
		List<String> adicionaveis = processadas.stream().filter(item -> item.erro() == null)
				.map(item -> item.organizacaoId()).toList();
		grupo.getOrganizacoes().addAll(adicionaveis);
		repository.salvar(grupo);
		reporter.reportEvent(Result.ofSuccess(new ResultadoProcessoVinculo(grupo, processadas)));
		return new Resposta(processadas);
	}

	private void processarVinculacao(String organizacaoId, GrupoEconomico grupo,
			List<OrganizacaoProcessada> processadas) {
		if (grupo.organizacaoVinculada(organizacaoId)) {
			processadas.add(new OrganizacaoProcessada(organizacaoId, GrupoEconomicoMessages.ORGANIZACAO_JA_VINCULADA));
		} else {
			organizacaoFacade.retornarOrganizacaoPorId(organizacaoId).ifPresentOrElse(
					orgDto -> processadas.add(new OrganizacaoProcessada(organizacaoId, null)), 
				() -> processadas.add(
							new OrganizacaoProcessada(organizacaoId,
									GrupoEconomicoMessages.ORGANIZACAO_NAO_ENCONTRADA)));
		}
	}


}
