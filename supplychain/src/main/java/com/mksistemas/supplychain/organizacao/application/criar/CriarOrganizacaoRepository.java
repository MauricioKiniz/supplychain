package com.mksistemas.supplychain.organizacao.application.criar;

import com.mksistemas.supplychain.organizacao.domain.Organizacao;

public interface CriarOrganizacaoRepository {
	boolean organizacaoExiste(String externalOrganizacaoId);

	Organizacao salvar(Organizacao organizacao);
}
