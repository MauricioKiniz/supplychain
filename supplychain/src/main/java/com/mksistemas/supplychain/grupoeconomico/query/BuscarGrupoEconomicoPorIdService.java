package com.mksistemas.supplychain.grupoeconomico.query;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.github.f4b6a3.tsid.Tsid;
import com.mksistemas.supplychain.grupoeconomico.BuscarGrupoEconomicoPorIdUseCase;
import com.mksistemas.supplychain.library.queryable.ProcessQuery;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
@Transactional(readOnly = true)
@Validated
class BuscarGrupoEconomicoPorIdService implements BuscarGrupoEconomicoPorIdUseCase {

	private final EntityManager entityManager;

	private static final String SELECTQUERY = """
			SELECT
				ge.grupo_economico_id grupoEconomicoId,
				ge.nome nome,
				ge.ativo ativo,
				oge.organizacao_id
			FROM
				gruposeconomicos ge
			LEFT JOIN
				organizacoes_grupoeconomico oge ON ge.grupo_economico_id = oge.grupo_economico_id
			WHERE
				oge.grupo_economico_id = ?1""";

	public BuscarGrupoEconomicoPorIdService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<GrupoEconomicoDto> execute(@Valid Requisicao request) {
		Long id = Tsid.from(request.grupoEconomicoId()).toLong();
		return ProcessQuery.<Object[]>of(entityManager)
				.ofType(Object[].class)
				.createQuery(SELECTQUERY)
				.withoutTransform()
				.setAdditional(nativeQuery -> nativeQuery.setParameter(1, id))
				.runAsStream()
				.collect(retornarGrupoEconomico());
	}

	private Collector<Object[], ?, Optional<GrupoEconomicoDto>> retornarGrupoEconomico() {
		return Collectors.collectingAndThen(mapToGrupoEconomicoDto(),
				(Map<Long, GrupoEconomicoDto> mapped) -> mapped.values().stream().findFirst());
	}

	private Collector<Object[], ?, Map<Long, GrupoEconomicoDto>> mapToGrupoEconomicoDto() {
		return Collectors.toMap(
				tuple -> (Long) tuple[0], tuple -> new GrupoEconomicoDto(Tsid.from((Long) tuple[0]).toLowerCase(),
						(String) tuple[1], (boolean) tuple[2], Arrays.asList(Tsid.from((Long) tuple[3]).toLowerCase())),
				(a, b) -> {
					a.organizacoes().addAll(b.organizacoes());
					return a;
				});
	}
}
