package com.mksistemas.supplychain.checklist.application.atualizar;

import java.util.ArrayList;
import java.util.List;

import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase.AtualizarChecklistRequisicao;
import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase.ChecklistRequisicao;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;
import com.mksistemas.supplychain.checklist.domain.ChecklistItem;
import com.mksistemas.supplychain.checklist.domain.ChecklistItemId;
import com.mksistemas.supplychain.checklist.domain.ValorItem;
import com.mksistemas.supplychain.checklist.domain.ValorItemEnum;

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

	private static List<ChecklistItem> criarItens(int total) {
		List<ValorItem> valores = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			valores.add(ValorItem.builder()
					.withExigeDescricao(ValorItemEnum.OBRIGATORIO)
					.withExigeDocumento(ValorItemEnum.OBRIGATORIO)
					.withNome("valor" + i).withSequencia(i).build());
		}
		List<ChecklistItem> itens = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			itens.add(ChecklistItem.builder()
					.withChecklistItemId(ChecklistItemId.generate())
					.withDescricao("descricao" + i).withNome("nome" + 1)
					.withValores(valores).build());
		}
		return itens;
	}

	public static Checklist retornarChecklistInsercaoItens() {
		return Checklist.builder().withChecklistId(ChecklistId.generate())
				.withNome("nomebase").withDescricao("descricaoBase")
				.withItems(criarItens(5)).build();
	}

}
