package com.mksistemas.supplychain.checklist.application.atualizar;

import java.util.Optional;

import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;

public interface AtualizarChecklistRepository {
	Optional<Checklist> buscarChecklist(ChecklistId checklistId);

	void salvar(Checklist checklist);

}
