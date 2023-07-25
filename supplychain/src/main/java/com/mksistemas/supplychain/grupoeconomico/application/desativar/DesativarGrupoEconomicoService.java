package com.mksistemas.supplychain.grupoeconomico.application.desativar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.grupoeconomico.DesativarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class DesativarGrupoEconomicoService implements DesativarGrupoEconomicoUseCase {

	private final DesativarGrupoEconomicoRepository repository;
	private final DesativarGrupoEconomicoReporter reporter;

	public DesativarGrupoEconomicoService(DesativarGrupoEconomicoRepository repository,
			DesativarGrupoEconomicoReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		GrupoEconomico grupo = repository.buscarPorId(requisicao.grupoEconomicoId())
				.orElseThrow(() -> new BusinessException(GrupoEconomicoMessages.GRUPO_NAO_ENCONTRADO));
		grupo.desativar();
		repository.salvar(grupo);
		reporter.reportEvent(Result.ofSuccess(grupo));
		return new Resposta(grupo.getGrupoEconomicoId().toLowerCase());
	}

}
