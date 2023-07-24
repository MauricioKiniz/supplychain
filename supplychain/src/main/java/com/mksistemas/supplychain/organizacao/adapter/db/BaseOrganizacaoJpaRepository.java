package com.mksistemas.supplychain.organizacao.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

public interface BaseOrganizacaoJpaRepository extends JpaRepository<Organizacao, OrganizacaoId> {
}
