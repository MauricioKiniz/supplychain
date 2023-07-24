package com.mksistemas.supplychain.organizacao;

import com.mksistemas.supplychain.library.processes.UseCase;

import jakarta.validation.constraints.NotBlank;

public interface CriarOrganizacaoUseCase
		extends UseCase<CriarOrganizacaoUseCase.Requisicao, CriarOrganizacaoUseCase.Resposta> {

	record Requisicao(@NotBlank(message = OrganizacaoMessages.EXTERNALID_BLANK) String idExterno,
			@NotBlank(message = OrganizacaoMessages.NOME_BLANK) String nome,
			@NotBlank(message = OrganizacaoMessages.CODIGOPAIS_BLANK) String codigoPais,
			@NotBlank(message = OrganizacaoMessages.IDENTIDADE_BLANK) String identidade, int timeZoneInSeconds) {

	}

	record Resposta(String organizacaoId) {
	}
}
