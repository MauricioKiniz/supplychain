package com.mksistemas.supplychain.organizacao.application.desativar;

import java.util.Optional;

import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

public interface DesativarOrganizacaoRepository {

	Optional<Organizacao> buscarOrganizacaoPorId(OrganizacaoId organizacaoId);

	Organizacao salvar(Organizacao organizacao);

}
