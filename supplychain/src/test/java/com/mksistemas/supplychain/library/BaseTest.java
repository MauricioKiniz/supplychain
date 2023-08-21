package com.mksistemas.supplychain.library;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BaseTest<TRequisicao, TResposta> {

	private Supplier<TRequisicao> givenSupplier;
	private Function<TRequisicao, TResposta> whenFunction;
	private BiConsumer<TRequisicao, TResposta> thenConsumer;
	private BiConsumer<TRequisicao, Exception> exceptionConsumer;

	public BaseTest<TRequisicao, TResposta> givenTest(Supplier<TRequisicao> givenSupplier) {
		this.givenSupplier = givenSupplier;
		return this;
	}

	public BaseTest<TRequisicao, TResposta> whenTest(Function<TRequisicao, TResposta> whenFunction) {
		this.whenFunction = whenFunction;
		return this;
	}

	public BaseTest<TRequisicao, TResposta> thenTest(BiConsumer<TRequisicao, TResposta> thenConsumer) {
		this.thenConsumer = thenConsumer;
		return this;
	}

	public BaseTest<TRequisicao, TResposta> onExceptionTest(BiConsumer<TRequisicao, Exception> exceptionConsumer) {
		this.exceptionConsumer = exceptionConsumer;
		return this;
	}

	public void runTest() {
		Objects.requireNonNull(givenSupplier);
		Objects.requireNonNull(whenFunction);
		Objects.requireNonNull(thenConsumer);

		boolean whenExecuted = false;
		TRequisicao requisicao = givenSupplier.get();
		try {
			TResposta resposta = whenFunction.apply(requisicao);
			whenExecuted = true;
			thenConsumer.accept(requisicao, resposta);
		} catch (Exception e) {
			if (Boolean.FALSE.equals(whenExecuted) && Objects.nonNull(exceptionConsumer)) {
				exceptionConsumer.accept(requisicao, e);
			} else
				throw e;
		}
	}
}
