package com.mksistemas.supplychain.organizacao;

import java.util.Optional;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;
import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

public interface BuscarOrganizacaoPorIdUseCase
		extends UseCase<BuscarOrganizacaoPorIdUseCase.Requisicao, Optional<OrganizacaoDto>> {
	record Requisicao(OrganizacaoId organizacaoId) {
	}
}
