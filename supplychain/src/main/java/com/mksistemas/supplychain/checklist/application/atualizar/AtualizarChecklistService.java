package com.mksistemas.supplychain.checklist.application.atualizar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase;
import com.mksistemas.supplychain.checklist.ChecklistMessages;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;
import com.mksistemas.supplychain.checklist.domain.ChecklistItem;
import com.mksistemas.supplychain.checklist.domain.ChecklistItemId;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class AtualizarChecklistService implements AtualizarChecklistUseCase {

	private final AtualizarChecklistRepository repository;
	private final AtualizarChecklistReporter reporter;

	public AtualizarChecklistService(AtualizarChecklistRepository repository, AtualizarChecklistReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public AtualizarChecklistResposta execute(@Valid AtualizarChecklistRequisicao requisicao) {
		Checklist checklist = repository.buscarChecklist(ChecklistId.from(requisicao.getChecklist().checklistId()))
				.orElseThrow(() -> new BusinessException(ChecklistMessages.CHECKLIST_NOT_FOUND));
		Contexto contexto = new Contexto(checklist, requisicao);
		preencherCheckList(contexto);
		aplicarItems(contexto);
		if (contexto.ocorreuAlteracao()) {
			repository.salvar(contexto.checklist);
			reporter.reportEvent(Result.ofSuccess(checklist));
		}
		return new AtualizarChecklistResposta(contexto.rejeitadas);
	}

	private void aplicarItems(Contexto contexto) {
		incluirNovosItens(contexto);
		alterarItens(contexto);
		removerItens(contexto);
	}

	private void removerItens(Contexto contexto) {
		contexto.getRequisicaoItems().stream().filter(item -> item.acao() == AtualizarChecklistUseCase.Acao.DEL)
				.forEach(item -> {
			RemocaoChecklistItem removerItem = (RemocaoChecklistItem) item.criarEntidade();
			ChecklistItemId id = ChecklistItemId.from(removerItem.getChecklistItemId());
			contexto.removerChecklistItem(id, removerItem);
		});
	}

	private void alterarItens(Contexto contexto) {
		contexto.getRequisicaoItems().stream().filter(item -> item.acao() == AtualizarChecklistUseCase.Acao.ALT)
				.forEach(item -> {
			AlterarChecklistItem alterarIncluido = (AlterarChecklistItem) item.criarEntidade();
			ChecklistItem itemAlterado = alterarIncluido.getItem()
					.montarChecklistItemEntity(ChecklistItemId.from(alterarIncluido.getChecklistItemId()));
			contexto.alterarChecklistItem(itemAlterado, alterarIncluido);
		});
	}

	private void incluirNovosItens(Contexto contexto) {
		contexto.getRequisicaoItems().stream().filter(item -> item.acao() == AtualizarChecklistUseCase.Acao.ADD)
				.forEach(item -> {
			IncluirChecklistItem itemIncluido = (IncluirChecklistItem)item.criarEntidade(); 
			ChecklistItem novoItem = itemIncluido.getItem().montarChecklistItemEntity(ChecklistItemId.generate());
			contexto.adicionarChecklistItem(novoItem);
		});
	}

	private void preencherCheckList(Contexto contexto) {
		AtualizarChecklistRequisicao requisicao = contexto.requisicao;
		if (Strings.isNotBlank(requisicao.getChecklist().nome())) {
			contexto.checklist.setNome(requisicao.getChecklist().nome());
			contexto.alteracaoChecklist = true;
		}
		String descricao = requisicao.getChecklist().descricao();
		if (Boolean.FALSE.equals(Objects.isNull(descricao))) {
			contexto.checklist.setDescricao(Strings.isEmpty(descricao) ? null : descricao);
			contexto.alteracaoChecklist = true;
		}
	}

	private class Contexto {
		private Checklist checklist;
		private AtualizarChecklistRequisicao requisicao;
		private List<AcaoRejeitada> rejeitadas = new ArrayList<>();
		private boolean inclusaoItem = false;
		private boolean alteracaoItem = false;
		private boolean remocaoItem = false;
		private boolean alteracaoChecklist = false;

		public Contexto(Checklist checklist, AtualizarChecklistRequisicao requisicao) {
			this.checklist = checklist;
			this.requisicao = requisicao;
		}

		public void adicionarChecklistItem(ChecklistItem item) {
			checklist.adicionarItem(item);
			inclusaoItem = true;
		}

		public void alterarChecklistItem(ChecklistItem item, AlterarChecklistItem alterarIncluido) {
			if (checklist.alterarItem(item))
				alteracaoItem = true;
			else
				rejeitadas.add(new AcaoRejeitada(alterarIncluido, ChecklistMessages.CHECKLISTITEM_NOT_FOUND));
		}

		public List<GenericAcaoChecklistItem> getRequisicaoItems() {
			return requisicao.getItems();
		}

		public void removerChecklistItem(ChecklistItemId id, RemocaoChecklistItem removerItem) {
			if (checklist.removerItemPorId(id))
				remocaoItem = true;
			else
				rejeitadas.add(new AcaoRejeitada(removerItem, ChecklistMessages.CHECKLISTITEM_NOT_FOUND));
		}

		public boolean ocorreuAlteracao() {
			return alteracaoChecklist || inclusaoItem || alteracaoItem || remocaoItem;
		}
	}
}
