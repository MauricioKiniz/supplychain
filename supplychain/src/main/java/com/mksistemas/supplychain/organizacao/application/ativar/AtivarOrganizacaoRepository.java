package com.mksistemas.supplychain.organizacao.application.ativar;

import java.util.Optional;

import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

public interface AtivarOrganizacaoRepository {
	Optional<Organizacao> buscarOrganizacaoPorId(OrganizacaoId id);

	Organizacao salvar(Organizacao organizacao);
}
