package com.mksistemas.supplychain.organizacao;

import com.mksistemas.supplychain.library.processes.UseCase;
import com.mksistemas.supplychain.library.validators.tsid.TsidId;

import jakarta.validation.constraints.NotBlank;

public interface AlterarOrganizacaoUseCase extends
		UseCase<AlterarOrganizacaoUseCase.Requisicao, AlterarOrganizacaoUseCase.Resposta> {
	record Requisicao(@TsidId(message = OrganizacaoMessages.ORGANIZACAO_ID_INVALIDO) String organizacaoId,
			@NotBlank(message = OrganizacaoMessages.EXTERNALID_BLANK) String idExterno,
			@NotBlank(message = OrganizacaoMessages.NOME_BLANK) String nome,
			@NotBlank(message = OrganizacaoMessages.CODIGOPAIS_BLANK) String codigoPais,
			@NotBlank(message = OrganizacaoMessages.IDENTIDADE_BLANK) String identidade, int timeZoneInSeconds) {
	}

	record Resposta(String organizacaoId) {
	}
}
