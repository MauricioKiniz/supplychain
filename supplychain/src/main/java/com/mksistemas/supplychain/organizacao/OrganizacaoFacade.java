package com.mksistemas.supplychain.organizacao;

import java.util.Optional;

import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

public interface OrganizacaoFacade {
	Optional<OrganizacaoDto> retornarOrganizacaoPorId(String organizacaoId);
}
