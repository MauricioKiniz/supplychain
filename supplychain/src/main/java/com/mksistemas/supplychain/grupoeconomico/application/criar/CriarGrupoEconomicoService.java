package com.mksistemas.supplychain.grupoeconomico.application.criar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.grupoeconomico.CriarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.railway.Result;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class CriarGrupoEconomicoService implements CriarGrupoEconomicoUseCase {

	private final CriarGrupoEconomicoRepository repository;
	private final CriarGrupoEconomicoReporter reporter;

	public CriarGrupoEconomicoService(CriarGrupoEconomicoRepository repository, CriarGrupoEconomicoReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		GrupoEconomico grupo = GrupoEconomico.builder().withGrupoEconomicoId(GrupoEconomicoId.generate())
				.withNome(requisicao.nome()).build();
		repository.salvar(grupo);
		reporter.reportEvent(Result.ofSuccess(grupo));
		return new Resposta(grupo.getGrupoEconomicoId().toLowerCase());
	}

}
