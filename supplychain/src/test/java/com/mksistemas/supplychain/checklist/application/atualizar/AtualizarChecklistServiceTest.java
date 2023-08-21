package com.mksistemas.supplychain.checklist.application.atualizar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase.AtualizarChecklistRequisicao;
import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase.AtualizarChecklistResposta;
import com.mksistemas.supplychain.checklist.ChecklistMessages;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;
import com.mksistemas.supplychain.library.BaseTest;
import com.mksistemas.supplychain.library.UseCaseBaseTest;
import com.mksistemas.supplychain.library.exceptions.BusinessException;

@ExtendWith(MockitoExtension.class)
class AtualizarChecklistServiceTest {

	@Mock
	AtualizarChecklistRepository repository;

	@Mock
	AtualizarChecklistReporter reporter;

	@InjectMocks
	AtualizarChecklistService service;

	@Captor
	ArgumentCaptor<Checklist> checklistCaptor;
	
	private BaseTest<AtualizarChecklistRequisicao, AtualizarChecklistResposta> tester;

	@BeforeEach
	void setUp() throws Exception {
		this.tester = UseCaseBaseTest.<AtualizarChecklistRequisicao, AtualizarChecklistResposta>of(
				service);
	}

	@Test
	void quandoAtualizacaoNaoEncontraChecklist() {
		tester.givenTest(() -> {
		    Mockito.when(repository.buscarChecklist(any(ChecklistId.class))).thenReturn(Optional.empty());
		    return AtualizarChacklistUseCaseSupplier.retornarChecklistNaoExistente();
		}).thenTest((requisicao, resposta) -> fail("Nao pode chegar aqui"))
			.onExceptionTest((requisicao, exception) -> {
			    assertInstanceOf(BusinessException.class, exception);
			    assertEquals(ChecklistMessages.CHECKLIST_NOT_FOUND, exception.getMessage());
			}).runTest();
	}

	private void captureChecklist() {
		doNothing().when(repository).salvar(checklistCaptor.capture());
	}

	private void mockBuscarChecklist(Checklist responseChecklist) {
	    when(repository.buscarChecklist(any(ChecklistId.class)))
		.thenReturn(Optional.of(responseChecklist));
	}

	@Test
	void quandoAtualizarDadosBaseChecklist() {
		tester.givenTest(() -> {
		    mockBuscarChecklist(AtualizarChacklistUseCaseSupplier.retornarChecklistBasico());
		    captureChecklist();
		    return AtualizarChacklistUseCaseSupplier.retornarChecklistDadosNomeDescricao();
		}).thenTest((requisicao, resposta) -> {
			Checklist checklist = checklistCaptor.getValue();
			assertEquals(checklist.getNome(), requisicao.getChecklist().nome());
			assertEquals(checklist.getDescricao(),
					requisicao.getChecklist().descricao());
		}).runTest();
	}

}
