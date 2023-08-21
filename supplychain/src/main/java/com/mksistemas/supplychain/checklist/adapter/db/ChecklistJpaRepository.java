package com.mksistemas.supplychain.checklist.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;

public interface ChecklistJpaRepository extends JpaRepository<Checklist, ChecklistId> {
}
