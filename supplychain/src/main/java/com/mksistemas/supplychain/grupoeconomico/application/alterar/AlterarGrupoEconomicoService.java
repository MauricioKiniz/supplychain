package com.mksistemas.supplychain.grupoeconomico.application.alterar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.grupoeconomico.AlterarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class AlterarGrupoEconomicoService implements AlterarGrupoEconomicoUseCase {

	private final AlterarGrupoEconomicoRepository repository;
	private final AlterarGrupoEconomicoReporter reporter;

	public AlterarGrupoEconomicoService(AlterarGrupoEconomicoRepository repository,
			AlterarGrupoEconomicoReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		GrupoEconomicoId id = GrupoEconomicoId.from(requisicao.grupoEconomicoId());
		GrupoEconomico grupo = repository.buscarPorId(id)
				.orElseThrow(() -> new BusinessException(GrupoEconomicoMessages.GRUPO_NAO_ENCONTRADO));
		grupo.setNome(requisicao.nome());
		repository.salvar(grupo);
		reporter.reportEvent(Result.ofSuccess(grupo));
		return new Resposta(grupo.getGrupoEconomicoId().toLowerCase());
	}

}
