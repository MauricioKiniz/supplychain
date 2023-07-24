package com.mksistemas.supplychain.library.railway;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Result<TFailure, TSuccess> {
	TFailure failure;
	private boolean isFailure;
	TSuccess success;

	private Result(TFailure failure, TSuccess success) {
		if (success == null && failure == null)
			throw new IllegalArgumentException("Either success and failure can not be null");
		this.isFailure = success == null && failure != null;
		this.failure = failure;
		this.success = success;
	}

	public Optional<TFailure> optionalFailure() {
		return isFailure ? Optional.ofNullable(failure) : Optional.empty();
	}

	public Optional<TSuccess> optionalSuccess() {
		return isFailure ? Optional.empty() : Optional.ofNullable(success);
	}

	public boolean isFailure() {
		return isFailure;
	}

	public boolean isSuccess() {
		return !isFailure;
	}

	public <TResult> TResult match(Function<TFailure, TResult> failureFunc, Function<TSuccess, TResult> successFunc) {
		return isFailure ? failureFunc.apply(this.failure) : successFunc.apply(this.success);
	}

	public Unit match(Consumer<TFailure> failureAction, Consumer<TSuccess> successAction) {
		return match(Helper.toFunc(failureAction, failure), Helper.toFunc(successAction, success));
	}

	// map methods
	public <TNewSuccess> Result<TFailure, TNewSuccess> map(Function<TSuccess, TNewSuccess> function) {
		return isSuccess() ? Result.ofSuccess(function.apply(success)) : Result.ofFailure(failure);
	}

	// of methods
	public static <TFailure, TSuccess> Result<TFailure, TSuccess> ofFailure(TFailure failure) {
		return new Result<>(failure, null);
	}

	public static <TFailure, TSuccess> Result<TFailure, TSuccess> ofSuccess(TSuccess success) {
		return new Result<>(null, success);
	}

	// then methods
	public <TNewSuccess> Result<TFailure, TNewSuccess> then(
			Function<TSuccess, Result<TFailure, TNewSuccess>> function) {
		return isSuccess() ? function.apply(success) : ofFailure(failure);
	}

    // throw on Failure

    public <TResult> TResult onFailureThrow(
        Function<TFailure, RuntimeException> failureFunc, Function<TSuccess, TResult> successFunc
    )
    {
        if (isFailure)
            throw failureFunc.apply(this.failure);
        return successFunc.apply(this.success);
    }


}
