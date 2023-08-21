package com.mksistemas.supplychain.checklist.application.atualizar;

import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase.AtualizarChecklistRequisicao;
import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase.ChecklistRequisicao;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;

public final class AtualizarChacklistUseCaseSupplier {
	public static AtualizarChecklistRequisicao retornarChecklistNaoExistente() {
		AtualizarChecklistRequisicao requisicao = new AtualizarChecklistRequisicao();
		requisicao.setChecklist(new ChecklistRequisicao("0d0evj90nyszn", null, null));
		return requisicao;
	}

	public static AtualizarChecklistRequisicao retornarChecklistDadosNomeDescricao() {
		AtualizarChecklistRequisicao requisicao = new AtualizarChecklistRequisicao();
		requisicao.setChecklist(new ChecklistRequisicao("0d0evj90nyszn",
				"novochecklist", "descricaonovochecklist"));
		return requisicao;
	}

	public static Checklist retornarChecklistBasico() {
		return Checklist.builder().withChecklistId(ChecklistId.generate())
				.withNome("nomebase").withDescricao("descricaoBase").build();
	}
}
