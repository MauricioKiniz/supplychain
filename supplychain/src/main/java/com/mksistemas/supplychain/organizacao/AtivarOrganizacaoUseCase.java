package com.mksistemas.supplychain.organizacao;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

public interface AtivarOrganizacaoUseCase
		extends UseCase<AtivarOrganizacaoUseCase.Requisicao, AtivarOrganizacaoUseCase.Resposta> {

	record Requisicao(OrganizacaoId organizacaoId) {
	}

	record Resposta(String organizacaoId) {
	}

}
