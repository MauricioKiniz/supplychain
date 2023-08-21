package com.mksistemas.supplychain.library;

import com.mksistemas.supplychain.library.processes.UseCase;

public class UseCaseBaseTest<TRequisicao, TResposta> extends BaseTest<TRequisicao, TResposta> {

	public UseCaseBaseTest(final UseCase<TRequisicao, TResposta> useCase) {
		whenTest(useCase::execute);
	}

	public static <TRequisicao, TResposta> BaseTest<TRequisicao, TResposta> of(
			UseCase<TRequisicao, TResposta> useCase) {
		return new UseCaseBaseTest<>(useCase);
	}

}
