package com.mksistemas.supplychain.grupoeconomico.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.grupoeconomico.BuscarTodosGruposUseCase;
import com.mksistemas.supplychain.library.queryable.ProcessQuery;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
@Transactional(readOnly = true)
@Validated
class BuscarGrupoEconomicoService implements BuscarTodosGruposUseCase {

	private static final String SELECTQUERY = """
			SELECT
				ge.grupo_economico_id grupoEconomicoId,
				ge.nome nome,
				ge.ativo ativo,
				(SELECT
				 	count(*)
				 FROM
				 	organizacoes_grupoeconomico oge
				 WHERE
				 	oge.grupo_economico_id = ge.grupo_economico_id) totalOrganizacoesVinculadas
			FROM
				gruposeconomicos ge""";

	private final EntityManager entityManager;

	public BuscarGrupoEconomicoService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<GrupoEconomicoDto> execute(@Valid Void request) {
		return ProcessQuery.<BuscarTodosGruposUseCase.GrupoEconomicoDto>of(entityManager).createQuery(SELECTQUERY)
				.ofType(BuscarTodosGruposUseCase.GrupoEconomicoDto.class).runAsList();
	}

}
