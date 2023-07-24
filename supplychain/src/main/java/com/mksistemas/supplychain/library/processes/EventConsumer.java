package com.mksistemas.supplychain.library.processes;

public interface EventConsumer<TEvent> {
	void execute(TEvent event);
}
