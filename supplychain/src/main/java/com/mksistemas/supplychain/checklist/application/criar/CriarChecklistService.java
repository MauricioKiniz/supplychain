package com.mksistemas.supplychain.checklist.application.criar;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mksistemas.supplychain.checklist.ChecklistMessages;
import com.mksistemas.supplychain.checklist.CriarChecklistUseCase;
import com.mksistemas.supplychain.checklist.application.vo.ChecklistItemDto;
import com.mksistemas.supplychain.checklist.application.vo.ChecklistValorDto;
import com.mksistemas.supplychain.checklist.application.vo.ExigeInformacaoEnum;
import com.mksistemas.supplychain.checklist.domain.Checklist;
import com.mksistemas.supplychain.checklist.domain.ChecklistId;
import com.mksistemas.supplychain.checklist.domain.ChecklistItem;
import com.mksistemas.supplychain.checklist.domain.ChecklistItemId;
import com.mksistemas.supplychain.checklist.domain.ValorItem;
import com.mksistemas.supplychain.checklist.domain.ValorItemEnum;
import com.mksistemas.supplychain.library.exceptions.BusinessException;
import com.mksistemas.supplychain.library.railway.Result;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
class CriarChecklistService implements CriarChecklistUseCase {

	private final CriarChecklistRepository repository;
	private final CriarChecklistReporter reporter;

	public CriarChecklistService(CriarChecklistRepository repository, CriarChecklistReporter reporter) {
		this.repository = repository;
		this.reporter = reporter;
	}

	@Override
	public CriarChecklistResposta execute(@Valid CriarChecklistRequisicao requisicao) {
		return validarRequisicao(requisicao)
				.then(this::montarEntidade)
				.then(this::salvarChecklist)
				.then(this::chamarReporter)
				.<CriarChecklistResposta>onFailureThrow(BusinessException::new,
						ctx -> new CriarChecklistResposta(ctx.getEntidade().getChecklistId().toLowerCase()));
	}

	private Result<String, CriarChecklistRequisicao> validarRequisicao(CriarChecklistRequisicao requisicao) {
		if (requisicao.getItems().isEmpty())
			Result.ofFailure(ChecklistMessages.ITENS_EMPTY);
		boolean todosTemValores = requisicao.getItems().stream()
				.allMatch(item -> Boolean.FALSE.equals(item.getValores().isEmpty()));
		if (Boolean.FALSE.equals(todosTemValores))
			Result.ofFailure(ChecklistMessages.VALORES_EMPTY);
		return Result.ofSuccess(requisicao);
	}

	private Result<String, Contexto> montarEntidade(CriarChecklistRequisicao requisicao) {

		List<ChecklistItem> itens = requisicao.getItems().stream()
				.collect(Collectors.mapping(this::montarItens, Collectors.toList()));

		Checklist checkList = Checklist.builder().withAtivo(true).withChecklistId(ChecklistId.generate())
				.withDescricao(requisicao.getDescricao()).withNome(requisicao.getNome()).build();

		checkList.getItems().addAll(itens);
		checkList.getItems().stream().forEach(item -> item.setChecklist(checkList));

		Contexto contexto = new Contexto();
		contexto.setDto(requisicao);
		contexto.setEntidade(checkList);
		return Result.ofSuccess(contexto);
	}

	private ValorItemEnum getValorItemEnumeracao(ExigeInformacaoEnum exige) {
		switch (exige) {
		case NAONECESSARIO:
			return ValorItemEnum.NAONECESSARIO;
		case OPCIONAL:
			return ValorItemEnum.OPCIONAL;
		case OBRIGATORIO:
			return ValorItemEnum.OBRIGATORIO;
		default:
			return ValorItemEnum.NAONECESSARIO;
		}
	}

	private ValorItem montarValor(ChecklistValorDto valorItem) {
		return ValorItem.builder().withExigeDescricao(getValorItemEnumeracao(valorItem.getExigeDescricao()))
				.withExigeDocumento(getValorItemEnumeracao(valorItem.getExigeDocumento())).withNome(valorItem.getNome())
				.withSequencia(valorItem.getSequencia()).build();
	}

	private ChecklistItem montarItens(ChecklistItemDto item) {
		List<ValorItem> valores = item.getValores().stream()
				.collect(Collectors.mapping(this::montarValor, Collectors.toList()));

		return ChecklistItem.builder().withChecklistItemId(ChecklistItemId.generate())
				.withDescricao(item.getDescricao()).withNome(item.getNome()).withValores(valores).build();
	}

	private Result<String, Contexto> salvarChecklist(Contexto contexto) {
		repository.salvar(contexto.getEntidade());
		return Result.ofSuccess(contexto);
	}

	private Result<String, Contexto> chamarReporter(Contexto contexto) {
		reporter.reportEvent(Result.ofSuccess(contexto.getEntidade()));
		return Result.ofSuccess(contexto);
	}

}
