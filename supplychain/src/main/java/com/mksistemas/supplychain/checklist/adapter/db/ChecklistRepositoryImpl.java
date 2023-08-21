package com.mksistemas.supplychain.checklist.adapter.db;

import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.checklist.application.criar.CriarChecklistRepository;
import com.mksistemas.supplychain.checklist.domain.Checklist;

@Component
class ChecklistRepositoryImpl implements CriarChecklistRepository {

	private final ChecklistJpaRepository checklistRepository;

	public ChecklistRepositoryImpl(ChecklistJpaRepository checklistRepository) {
		this.checklistRepository = checklistRepository;
	}

	@Override
	public void salvar(Checklist checklist) {
		checklistRepository.save(checklist);
	}

}
