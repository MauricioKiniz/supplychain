package com.mksistemas.supplychain.grupoeconomico.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mksistemas.supplychain.grupoeconomico.AlterarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.CriarGrupoEconomicoUseCase;

@RestController
@RequestMapping("/api/v1/gruposeconomicos")
public class GrupoEconomicoController {

	private final CriarGrupoEconomicoUseCase criarGrupoEconomico;
	private final AlterarGrupoEconomicoUseCase alterarGrupoEconomico;

	public GrupoEconomicoController(CriarGrupoEconomicoUseCase criarGrupoEconomico,
			AlterarGrupoEconomicoUseCase alterarGrupoEconomico) {
		this.criarGrupoEconomico = criarGrupoEconomico;
		this.alterarGrupoEconomico = alterarGrupoEconomico;
	}

	@PostMapping
	public ResponseEntity<CriarGrupoEconomicoUseCase.Resposta> criarOrganizacao(
			@RequestBody CriarGrupoEconomicoUseCase.Requisicao requisicao) {
		CriarGrupoEconomicoUseCase.Resposta response = criarGrupoEconomico.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<AlterarGrupoEconomicoUseCase.Resposta> alterarOrganizacao(
			@RequestBody AlterarGrupoEconomicoUseCase.Requisicao requisicao) {
		AlterarGrupoEconomicoUseCase.Resposta response = alterarGrupoEconomico.execute(requisicao);
		return ResponseEntity.ok(response);
	}

}
