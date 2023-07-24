package com.mksistemas.supplychain.library.processes;

import jakarta.validation.Valid;

public interface UseCase<TRequest, TResponse> {
	TResponse execute(@Valid TRequest request);
}
