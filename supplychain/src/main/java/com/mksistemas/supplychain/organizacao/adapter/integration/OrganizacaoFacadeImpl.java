package com.mksistemas.supplychain.organizacao.adapter.integration;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.organizacao.OrganizacaoFacade;
import com.mksistemas.supplychain.organizacao.BuscarOrganizacaoPorIdUseCase;
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
		BuscarOrganizacaoPorIdUseCase useCase = context.getBean(BuscarOrganizacaoPorIdUseCase.class);
		return useCase.execute(new BuscarOrganizacaoPorIdUseCase.Requisicao(OrganizacaoId.from(organizacaoId)));
	}

}
