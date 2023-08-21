package com.mksistemas.supplychain.checklist.application.criar;

import com.mksistemas.supplychain.checklist.domain.Checklist;

public interface CriarChecklistRepository {
	void salvar(Checklist checklist);
}
