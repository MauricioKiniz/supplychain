package com.mksistemas.supplychain.checklist;

import com.mksistemas.supplychain.checklist.application.vo.ChecklistDto;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.library.processes.UseCase;

public interface CriarChecklistUseCase
		extends UseCase<CriarChecklistUseCase.CriarChecklistRequisicao, CriarChecklistUseCase.CriarChecklistResposta> {

	class CriarChecklistRequisicao extends ChecklistDto {
	}

	record CriarChecklistResposta(String checklistId) {
	}

	class Contexto {
		private CriarChecklistRequisicao dto;
		private Checklist entidade;

		public CriarChecklistRequisicao getDto() {
			return dto;
		}

		public void setDto(CriarChecklistRequisicao dto) {
			this.dto = dto;
		}

		public Checklist getEntidade() {
			return entidade;
		}

		public void setEntidade(Checklist entidade) {
			this.entidade = entidade;
		}
	}

}
