package com.mksistemas.supplychain.organizacao.adapter.api;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mksistemas.supplychain.organizacao.AlterarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.AtivarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.CriarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.DesativarOrganizacaoUseCase;
import com.mksistemas.supplychain.organizacao.BuscarOrganizacaoPorIdUseCase;
import com.mksistemas.supplychain.organizacao.BuscarTodasOrganizacoesUseCase;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;
import com.mksistemas.supplychain.organizacao.query.vo.OrganizacaoDto;

@RestController
@RequestMapping("/api/v1/organizacoes")
public class OrganizacaoController {

	private final CriarOrganizacaoUseCase criarOrganizacao;
	private final AlterarOrganizacaoUseCase alterarOrganizacao;
	private final AtivarOrganizacaoUseCase ativarOrganizacao;
	private final DesativarOrganizacaoUseCase desativarOrganizacao;
	private final BuscarTodasOrganizacoesUseCase buscarTodasOrganizacoes;
	private final BuscarOrganizacaoPorIdUseCase vuscarOrganizacaoPorId;

	public OrganizacaoController(final CriarOrganizacaoUseCase criarOrganizacao,
			final AlterarOrganizacaoUseCase alterarOrganizacao, final AtivarOrganizacaoUseCase ativarOrganizacao,
			final DesativarOrganizacaoUseCase desativarOrganizacao,
			final BuscarTodasOrganizacoesUseCase buscarTodasOrganizacoes,
			final BuscarOrganizacaoPorIdUseCase buscarOrganizacaoPorId) {
		this.criarOrganizacao = criarOrganizacao;
		this.alterarOrganizacao = alterarOrganizacao;
		this.ativarOrganizacao = ativarOrganizacao;
		this.desativarOrganizacao = desativarOrganizacao;
		this.buscarTodasOrganizacoes = buscarTodasOrganizacoes;
		this.vuscarOrganizacaoPorId = buscarOrganizacaoPorId;
	}

	@PostMapping
	public ResponseEntity<CriarOrganizacaoUseCase.Resposta> criarOrganizacao(
			@RequestBody CriarOrganizacaoUseCase.Requisicao requisicao) {
		CriarOrganizacaoUseCase.Resposta response = criarOrganizacao.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<AlterarOrganizacaoUseCase.Resposta> ativarOrganizacao(
			@RequestBody AlterarOrganizacaoUseCase.Requisicao requisicao) {
		AlterarOrganizacaoUseCase.Resposta response = alterarOrganizacao.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@PatchMapping(path = "/ativar/{organizacaoId}")
	public ResponseEntity<AtivarOrganizacaoUseCase.Resposta> ativarOrganizacao(
			@PathVariable OrganizacaoId organizacaoId) {
		AtivarOrganizacaoUseCase.Resposta response = ativarOrganizacao
				.execute(new AtivarOrganizacaoUseCase.Requisicao(organizacaoId));
		return ResponseEntity.ok(response);
	}

	@PatchMapping(path = "/desativar/{organizacaoId}")
	public ResponseEntity<DesativarOrganizacaoUseCase.Resposta> desativarOrganizacao(
			@PathVariable OrganizacaoId organizacaoId) {
		DesativarOrganizacaoUseCase.Resposta response = desativarOrganizacao
				.execute(new DesativarOrganizacaoUseCase.Requisicao(organizacaoId));
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<Slice<OrganizacaoDto>> buscarTodasOrganizacoes(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "25") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		BuscarTodasOrganizacoesUseCase.Requisicao requisicao = new BuscarTodasOrganizacoesUseCase.Requisicao(
				pageable);
		Slice<OrganizacaoDto> response = buscarTodasOrganizacoes.execute(requisicao);
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{organizacaoId}")
	public ResponseEntity<OrganizacaoDto> buscarOrganizacaoPorId(
			@PathVariable(required = true) OrganizacaoId organizacaoId) {
		Optional<OrganizacaoDto> organizacao = vuscarOrganizacaoPorId
				.execute(new BuscarOrganizacaoPorIdUseCase.Requisicao(organizacaoId));
		if (organizacao.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(organizacao.get());
	}


}
