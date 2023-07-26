package com.mksistemas.supplychain.organizacao.adapter.integration;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.organizacao.OrganizacaoFacade;
import com.mksistemas.supplychain.organizacao.RetornarOrganizacaoPorIdUseCase;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;
import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

@Component
class OrganizacaoFacadeImpl implements OrganizacaoFacade {

	private final ApplicationContext context;

	public OrganizacaoFacadeImpl(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public Optional<OrganizacaoDto> retornarOrganizacaoPorId(String organizacaoId) {
		RetornarOrganizacaoPorIdUseCase useCase = context.getBean(RetornarOrganizacaoPorIdUseCase.class);
		return useCase.execute(new RetornarOrganizacaoPorIdUseCase.Requisicao(OrganizacaoId.from(organizacaoId)));
	}

}
