package com.mksistemas.supplychain.organizacao.query;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.library.queryable.ProcessQuery;
import com.mksistemas.supplychain.organizacao.RetornarTodasOrganizacoesUseCase;
import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
@Transactional(readOnly = true)
@Validated
class RetornarTodasOrganizacoesService implements RetornarTodasOrganizacoesUseCase {

	private final EntityManager entityManager;

	public RetornarTodasOrganizacoesService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Slice<OrganizacaoDto> execute(@Valid Requisicao request) {
		return ProcessQuery.<OrganizacaoDto>of(entityManager).createQuery("""
					SELECT
						organizacao_id organizacaoId,
						ativo,
						codigo_pais codigoPais,
						id_externo idExterno,
						identidade,
						nome,
						time_zone_in_seconds timeZoneInSeconds
					FROM organizacoes
				""").ofType(OrganizacaoDto.class).setPageable(request.page())
				.runAsSlice();
	}

	/*
	 * (tuples) -> OrganizacaoDto.builder().withOrganizacaoId((String) tuples[0])
	 * .withAtivo((boolean) tuples[1]).withCodigoPais((String) tuples[2])
	 * .withIdExterno((String) tuples[3]).withIdentidade((String) tuples[4])
	 * .withNome((String) tuples[5]).withTimeZoneInSeconds((int) tuples[6]).build()
	 * *
	 */

}
