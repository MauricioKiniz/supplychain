package com.mksistemas.supplychain.library.reporter;

import java.util.function.Function;

import org.springframework.context.ApplicationEventPublisher;

import com.mksistemas.supplychain.library.railway.Result;

public interface BaseReporterEvent<TEvent, TEntity> {
	default void publicarEmCasoSucesso(Result<Void, TEntity> eventToReport, Function<TEntity, TEvent> criarEvento,
			ApplicationEventPublisher applicationEventPublisher) {
		if (eventToReport.isSuccess()) {
			TEntity entity = eventToReport.optionalSuccess().get();
			TEvent event = criarEvento.apply(entity);
			applicationEventPublisher.publishEvent(event);
		}
	}

}
