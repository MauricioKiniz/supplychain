package com.mksistemas.supplychain.organizacao.application.alterar;

import java.util.Optional;

import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

public interface AlterarOrganizacaoRepository {
	boolean organizacaoExiste(OrganizacaoId organizacaoId, String idExterno);

	Organizacao salvar(Organizacao organizacao);

	Optional<Organizacao> buscarOrganizacaoPorId(OrganizacaoId id);
}
