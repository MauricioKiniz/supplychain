package com.mksistemas.supplychain.grupoeconomico;

import java.util.List;

import org.springframework.lang.Nullable;

import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.processes.UseCase;

import jakarta.validation.constraints.NotNull;

public interface VincularOrganizacaoUseCase
		extends UseCase<VincularOrganizacaoUseCase.Requisicao, VincularOrganizacaoUseCase.Resposta> {

	record Requisicao(@NotNull(message = GrupoEconomicoMessages.GRUPO_ID_NULL) GrupoEconomicoId grupoEconomicoId,
			@NotNull(message = GrupoEconomicoMessages.GRUPO_LISTA_ORGANIZACOES) List<String> organizacoes) {
	}
	
	record Resposta(List<OrganizacaoProcessada> organizacoesProcessadas) {
	}

	record OrganizacaoProcessada(String organizacaoId, @Nullable String erro) {
	}

}
