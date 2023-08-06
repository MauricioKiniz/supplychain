package com.mksistemas.supplychain.grupoeconomico.adapter.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mksistemas.supplychain.grupoeconomico.AlterarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.AtivarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.BuscarGrupoEconomicoPorIdUseCase;
import com.mksistemas.supplychain.grupoeconomico.BuscarTodosGruposUseCase;
import com.mksistemas.supplychain.grupoeconomico.CriarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.DesativarGrupoEconomicoUseCase;
import com.mksistemas.supplychain.grupoeconomico.DesvincularOrganizacaoUseCase;
import com.mksistemas.supplychain.grupoeconomico.GrupoEconomicoMessages;
import com.mksistemas.supplychain.grupoeconomico.VincularOrganizacaoUseCase;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;
import com.mksistemas.supplychain.library.validators.tsid.TsidId;

@RestController
@RequestMapping("/api/v1/gruposeconomicos")
public class GrupoEconomicoController {

	private final CriarGrupoEconomicoUseCase criarGrupoEconomico;
	private final AlterarGrupoEconomicoUseCase alterarGrupoEconomico;
	private final AtivarGrupoEconomicoUseCase ativarGrupoEconomico;
	private final DesativarGrupoEconomicoUseCase desativarGrupoEconomico;
	private final VincularOrganizacaoUseCase vincularOrganizacao;
	private final DesvincularOrganizacaoUseCase desvincularOrganizacao;
	private final BuscarTodosGruposUseCase buscarTodosGrupos;
	private final BuscarGrupoEconomicoPorIdUseCase buscarGrupoPorId;

	public GrupoEconomicoController(CriarGrupoEconomicoUseCase criarGrupoEconomico,
			AlterarGrupoEconomicoUseCase alterarGrupoEconomico, AtivarGrupoEconomicoUseCase ativarGrupoEconomico,
			DesativarGrupoEconomicoUseCase desativarGrupoEconomico, VincularOrganizacaoUseCase vincularOrganizacao,
			DesvincularOrganizacaoUseCase desvincularOrganizacao, BuscarTodosGruposUseCase buscarTodosGrupos,
			BuscarGrupoEconomicoPorIdUseCase buscarGrupoPorId) {
		this.criarGrupoEconomico = criarGrupoEconomico;
		this.alterarGrupoEconomico = alterarGrupoEconomico;
		this.ativarGrupoEconomico = ativarGrupoEconomico;
		this.desativarGrupoEconomico = desativarGrupoEconomico;
		this.vincularOrganizacao = vincularOrganizacao;
		this.desvincularOrganizacao = desvincularOrganizacao;
		this.buscarTodosGrupos = buscarTodosGrupos;
		this.buscarGrupoPorId = buscarGrupoPorId;
	}

	@PostMapping
	public ResponseEntity<CriarGrupoEconomicoUseCase.Resposta> criarGrupoEconomico(
			@RequestBody CriarGrupoEconomicoUseCase.Requisicao requisicao) {
		CriarGrupoEconomicoUseCase.Resposta response = criarGrupoEconomico.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<AlterarGrupoEconomicoUseCase.Resposta> alterarGrupoEconomico(
			@RequestBody AlterarGrupoEconomicoUseCase.Requisicao requisicao) {
		AlterarGrupoEconomicoUseCase.Resposta response = alterarGrupoEconomico.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PatchMapping(path = "/ativar/{grupoId}")
	public ResponseEntity<AtivarGrupoEconomicoUseCase.Resposta> ativarGrupoEconomico(
			@PathVariable(required = true, name = "grupoId") @TsidId(message = GrupoEconomicoMessages.GRUPO_ID_INCORRETO) String grupoId) {
		AtivarGrupoEconomicoUseCase.Resposta response = ativarGrupoEconomico
				.execute(new AtivarGrupoEconomicoUseCase.Requisicao(GrupoEconomicoId.from(grupoId)));
		return ResponseEntity.ok(response);
	}

	@PatchMapping(path = "/desativar/{grupoId}")
	public ResponseEntity<DesativarGrupoEconomicoUseCase.Resposta> desativarGrupoEconomico(
			@PathVariable(required = true, name = "grupoId") @TsidId(message = GrupoEconomicoMessages.GRUPO_ID_INCORRETO) String grupoId) {
		DesativarGrupoEconomicoUseCase.Resposta response = desativarGrupoEconomico
				.execute(new DesativarGrupoEconomicoUseCase.Requisicao(GrupoEconomicoId.from(grupoId)));
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/vincular")
	public ResponseEntity<VincularOrganizacaoUseCase.Resposta> vincularOrganizacao(
			@RequestBody VincularOrganizacaoUseCase.Requisicao requisicao) {
		VincularOrganizacaoUseCase.Resposta response = vincularOrganizacao.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/desvincular")
	public ResponseEntity<DesvincularOrganizacaoUseCase.Resposta> desvincularOrganizacao(
			@RequestBody DesvincularOrganizacaoUseCase.Requisicao requisicao) {
		DesvincularOrganizacaoUseCase.Resposta response = desvincularOrganizacao.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<BuscarTodosGruposUseCase.GrupoEconomicoDto>> buscarTodosGrupos() {
		return ResponseEntity.ok(buscarTodosGrupos.execute(null));
	}

	@GetMapping(path = "/{grupoEconomicoId}")
	public ResponseEntity<BuscarGrupoEconomicoPorIdUseCase.GrupoEconomicoDto> buscarGrupoEconomicoPorId(
			@PathVariable String grupoEconomicoId) {
		Optional<BuscarGrupoEconomicoPorIdUseCase.GrupoEconomicoDto> resposta = buscarGrupoPorId
				.execute(new BuscarGrupoEconomicoPorIdUseCase.Requisicao(grupoEconomicoId));
		return resposta.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(resposta.get());
	}

}
