package com.mksistemas.supplychain.organizacao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.lang.Nullable;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

public interface RetornarTodasOrganizacoesUseCase
		extends
		UseCase<RetornarTodasOrganizacoesUseCase.Requisicao, Slice<OrganizacaoDto>> {

	record Requisicao(@Nullable Pageable page) {
	}

}
