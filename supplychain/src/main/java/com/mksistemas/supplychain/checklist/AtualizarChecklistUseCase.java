package com.mksistemas.supplychain.checklist;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mksistemas.supplychain.checklist.application.vo.ChecklistItemDto;
import com.mksistemas.supplychain.library.processes.UseCase;

import jakarta.validation.constraints.NotBlank;

public interface AtualizarChecklistUseCase extends
		UseCase<AtualizarChecklistUseCase.AtualizarChecklistRequisicao, AtualizarChecklistUseCase.AtualizarChecklistResposta> {

	class AtualizarChecklistRequisicao {
		private ChecklistRequisicao checklist;
		private List<GenericAcaoChecklistItem> items = new ArrayList<>();

		public ChecklistRequisicao getChecklist() {
			return checklist;
		}
		public void setChecklist(ChecklistRequisicao checklist) {
			this.checklist = checklist;
		}
		public List<GenericAcaoChecklistItem> getItems() {
			return items;
		}
		public void setItems(List<GenericAcaoChecklistItem> items) {
			this.items = items;
		}
	}
	
	record AtualizarChecklistResposta(List<AcaoRejeitada> acoesRejeitadas) {
	}

	record ChecklistRequisicao(@NotBlank(message = ChecklistMessages.CHECKLIST_ID_BLANK) String checklistId,
			String nome, String descricao) {
	}
	
	record AcaoRejeitada(AcaoChecklistItem acaoItem, String error) {
	}
	
	enum Acao {
		ADD, DEL, ALT
	}

	@JsonTypeInfo(use = Id.NAME, property = "acao", include = As.PROPERTY, visible = true)

	@JsonSubTypes({ @Type(name = "add", value = AcaoInclusaoChecklistItem.class),
			@Type(name = "del", value = AcaoRemocaoChecklistItem.class),
			@Type(name = "alt", value = AcaoAlteracaoChecklistItem.class) })
	public interface GenericAcaoChecklistItem {
		Acao acao();

		AcaoChecklistItem criarEntidade();
	}

	abstract class AcaoChecklistItem {
		private String checklistItemId;

		AcaoChecklistItem(String checklistItemId) {
			this.checklistItemId = checklistItemId;
		}

		public String getChecklistItemId() {
			return checklistItemId;
		}

		public void setChecklistItemId(String checklistItemId) {
			this.checklistItemId = checklistItemId;
		}
	}

	class RemocaoChecklistItem extends AcaoChecklistItem {
		@NotBlank
		public RemocaoChecklistItem(String checklistItemId) {
			super(checklistItemId);
		}
	}

	class AlterarChecklistItem extends AcaoChecklistItem {
		public AlterarChecklistItem(String checklistItemId, ChecklistItemDto item) {
			super(checklistItemId);
			this.item = item;
		}

		private ChecklistItemDto item;

		public ChecklistItemDto getItem() {
			return item;
		}

		public void setItem(ChecklistItemDto item) {
			this.item = item;
		}
	}

	class IncluirChecklistItem extends AcaoChecklistItem {
		public IncluirChecklistItem(ChecklistItemDto item) {
			super(Strings.EMPTY);
			this.item = item;
		}

		private ChecklistItemDto item;

		public ChecklistItemDto getItem() {
			return item;
		}

		public void setItem(ChecklistItemDto item) {
			this.item = item;
		}
	}

	record AcaoRemocaoChecklistItem(Acao acao, String checklistItemId) implements GenericAcaoChecklistItem {

		@Override
		public AcaoChecklistItem criarEntidade() {
			return new RemocaoChecklistItem(checklistItemId);
		}
	}

	record AcaoAlteracaoChecklistItem(Acao acao, String checklistItemId, ChecklistItemDto item)
			implements GenericAcaoChecklistItem {

		@Override
		public AcaoChecklistItem criarEntidade() {
			return new AlterarChecklistItem(checklistItemId, item);
		}
	}

	record AcaoInclusaoChecklistItem(Acao acao, ChecklistItemDto item)
			implements GenericAcaoChecklistItem {

		@Override
		public AcaoChecklistItem criarEntidade() {
			return new IncluirChecklistItem(item);
		}
	}

}
