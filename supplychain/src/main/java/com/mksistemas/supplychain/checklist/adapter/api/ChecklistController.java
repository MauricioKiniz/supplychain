package com.mksistemas.supplychain.checklist.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mksistemas.supplychain.checklist.AtualizarChecklistUseCase;
import com.mksistemas.supplychain.checklist.CriarChecklistUseCase;
import com.mksistemas.supplychain.checklist.CriarChecklistUseCase.CriarChecklistRequisicao;
import com.mksistemas.supplychain.checklist.CriarChecklistUseCase.CriarChecklistResposta;

@RestController
@RequestMapping("/api/v1/checklists")
public class ChecklistController {

	private final CriarChecklistUseCase criarChecklist;
	private final AtualizarChecklistUseCase atualizarChecklist;

	public ChecklistController(CriarChecklistUseCase criarChecklist, AtualizarChecklistUseCase atualizarChecklist) {
		this.criarChecklist = criarChecklist;
		this.atualizarChecklist = atualizarChecklist;
	}

	@PostMapping
	public ResponseEntity<CriarChecklistResposta> criarChecklist(@RequestBody CriarChecklistRequisicao requisicao) {
		CriarChecklistResposta response = criarChecklist.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<AtualizarChecklistUseCase.AtualizarChecklistResposta> alterarChecklist(
			@RequestBody AtualizarChecklistUseCase.AtualizarChecklistRequisicao requisicao) {
		AtualizarChecklistUseCase.AtualizarChecklistResposta response = atualizarChecklist.execute(requisicao);
		return ResponseEntity.ok(response);
	}

}
