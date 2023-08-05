package com.mksistemas.supplychain.grupoeconomico.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.github.f4b6a3.tsid.Tsid;
import com.mksistemas.supplychain.library.queryable.ProcessQuery;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
@Transactional(readOnly = true)
@Validated
class BuscarGruposEconomicosPorOrganizacaoService
		implements com.mksistemas.supplychain.grupoeconomico.BuscarGruposEconomicosPorOrganizacaoUseCase {

	private final EntityManager entityManager;

	public BuscarGruposEconomicosPorOrganizacaoService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public Resposta execute(@Valid Requisicao requisicao) {
		Long id = Tsid.from(requisicao.organizacaoId()).toLong();
		List<Long> resultado = ProcessQuery.<Long>of(entityManager)
				.createQuery(
						"select grupo_economico_id from organizacoes_grupoeconomico where organizacao_id = ?1")
				.ofType(Long.class).setAdditional(nquery -> nquery.setParameter(1, id))
				.setTransform(data -> (Long) data[0])
				.runAsList();
		return new Resposta(resultado.stream().map(grupoId -> Tsid.from(grupoId).toLowerCase()).toList());
	}

}
