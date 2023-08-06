package com.mksistemas.supplychain.organizacao.query;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.library.queryable.ProcessQuery;
import com.mksistemas.supplychain.organizacao.BuscarOrganizacaoPorIdUseCase;
import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
@Transactional(readOnly = true)
@Validated
class BuscarOrganizacaoPorIdService implements BuscarOrganizacaoPorIdUseCase {

	private final EntityManager manager;

	public BuscarOrganizacaoPorIdService(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Optional<OrganizacaoDto> execute(@Valid Requisicao requisicao) {
		return ProcessQuery.<OrganizacaoDto>of(manager).createQuery(
				"""
					SELECT
						organizacao_id organizacaoId,
						ativo,
						codigo_pais codigoPais,
						id_externo idExterno,
						identidade,
						nome,
						time_zone_in_seconds timeZoneInSeconds
					FROM
						organizacoes
					WHERE
						organizacao_id = ?1
				""")
				.ofType(OrganizacaoDto.class)
				.setAdditional(nativeQuery -> nativeQuery.setParameter(1, requisicao.organizacaoId().getId().toLong()))
				.runSingle();
	}

}
