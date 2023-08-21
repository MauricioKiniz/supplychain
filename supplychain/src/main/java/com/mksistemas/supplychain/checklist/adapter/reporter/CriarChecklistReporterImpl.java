package com.mksistemas.supplychain.checklist.adapter.reporter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.checklist.adapter.reporter.events.ChecklistCriadoEvent;
import com.mksistemas.supplychain.checklist.application.criar.CriarChecklistReporter;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.library.railway.Result;
import com.mksistemas.supplychain.library.reporter.BaseReporterEvent;

@Component
class CriarChecklistReporterImpl
		implements BaseReporterEvent<ChecklistCriadoEvent, Checklist>, CriarChecklistReporter {

	private final ApplicationEventPublisher applicationEventPublisher;

	public CriarChecklistReporterImpl(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void reportEvent(Result<Void, Checklist> eventToReport) {
		publicarEmCasoSucesso(eventToReport,
				checklist -> ChecklistCriadoEvent.builder().withChecklistId(checklist.getChecklistId().toLowerCase())
						.withDescricao(checklist.getDescricao()).withNome(checklist.getNome()).build(),
				applicationEventPublisher);
	}

}
