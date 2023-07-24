package com.mksistemas.supplychain.organizacao;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

public interface DesativarOrganizacaoUseCase
		extends UseCase<DesativarOrganizacaoUseCase.Requisicao, DesativarOrganizacaoUseCase.Resposta> {

	record Requisicao(OrganizacaoId organizacaoId) {
	}

	record Resposta(String organizacaoId) {
	}
}
