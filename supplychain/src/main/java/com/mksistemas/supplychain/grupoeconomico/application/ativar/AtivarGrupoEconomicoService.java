package com.mksistemas.supplychain.grupoeconomico.application.ativar;

import com.mksistemas.supplychain.grupoeconomico.AtivarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;

import jakarta.validation.Valid;

class AtivarGrupoEconomicoService implements AtivarGrupoEconomicoUseCase {

	private final AtivarGrupoEconomicoRepository repository;
	private final AtivarGrupoEconomicoReporter reporter;

	public AtivarGrupoEconomicoService(AtivarGrupoEconomicoRepository repository,
			AtivarGrupoEconomicoReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		GrupoEconomico grupo = repository.buscarPorId(requisicao.grupoEconomicoId())
				.orElseThrow(() -> new BusinessException(GrupoEconomicoMessages.GRUPO_NAO_ENCONTRADO));
		grupo.ativar();
		repository.salvar(grupo);
		reporter.reportEvent(Result.ofSuccess(grupo));
		return new Resposta(grupo.getGrupoEconomicoId().toLowerCase());
	}

}
