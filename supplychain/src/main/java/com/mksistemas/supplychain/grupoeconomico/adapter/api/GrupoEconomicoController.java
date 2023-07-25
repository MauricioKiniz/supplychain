package com.mksistemas.supplychain.grupoeconomico.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mksistemas.supplychain.grupoeconomico.AlterarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.AtivarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.CriarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.validators.tsid.TsidId;

@RestController
@RequestMapping("/api/v1/gruposeconomicos")
public class GrupoEconomicoController {

	private final CriarGrupoEconomicoUseCase criarGrupoEconomico;
	private final AlterarGrupoEconomicoUseCase alterarGrupoEconomico;
	private final AtivarGrupoEconomicoUseCase ativarGrupoEconomico;

	public GrupoEconomicoController(CriarGrupoEconomicoUseCase criarGrupoEconomico,
			AlterarGrupoEconomicoUseCase alterarGrupoEconomico, AtivarGrupoEconomicoUseCase atibarGrupoEconomico) {
		this.criarGrupoEconomico = criarGrupoEconomico;
		this.alterarGrupoEconomico = alterarGrupoEconomico;
		this.ativarGrupoEconomico = atibarGrupoEconomico;
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

	@PatchMapping(path = "/ativar/{grupoId}")
	public ResponseEntity<AtivarGrupoEconomicoUseCase.Resposta> ativarOrganizacao(
			@PathVariable(required = true, name = "grupoId") @TsidId(message = GrupoEconomicoMessages.GRUPO_ID_INCORRETO) String grupoId) {
		AtivarGrupoEconomicoUseCase.Resposta response = ativarGrupoEconomico
				.execute(new AtivarGrupoEconomicoUseCase.Requisicao(GrupoEconomicoId.from(grupoId)));
		return ResponseEntity.ok(response);
	}

}
