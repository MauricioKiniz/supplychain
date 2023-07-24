package com.mksistemas.supplychain.organizacao.adapter.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.organizacao.application.alterar.AlterarOrganizacaoRepository;
import com.mksistemas.supplychain.organizacao.application.ativar.AtivarOrganizacaoRepository;
import com.mksistemas.supplychain.organizacao.application.criar.CriarOrganizacaoRepository;
import com.mksistemas.supplychain.organizacao.application.desativar.DesativarOrganizacaoRepository;
import com.mksistemas.supplychain.organizacao.domain.Organizacao;
import com.mksistemas.supplychain.organizacao.domain.OrganizacaoId;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Component
class OrganizacaoJpaRepository implements CriarOrganizacaoRepository, AlterarOrganizacaoRepository,
		AtivarOrganizacaoRepository, DesativarOrganizacaoRepository {

	private final BaseOrganizacaoJpaRepository repository;
	private final EntityManager entityManager;

	public OrganizacaoJpaRepository(final BaseOrganizacaoJpaRepository repository, final EntityManager entityManager) {
		this.repository = repository;
		this.entityManager = entityManager;
	}

	@Override
	public boolean organizacaoExiste(String idExterno) {
		try {
			entityManager.createNativeQuery("select 1 from organizacoes orgs where orgs.id_externo = ?1")
					.setParameter(1, idExterno).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	@Override
	public boolean organizacaoExiste(OrganizacaoId organizacaoId, String idExterno) {
		try {
			Long id = organizacaoId.getId().toLong();
			entityManager
					.createNativeQuery(
							"select 1 from organizacoes orgs where orgs.organizacao_id <> ?1 and orgs.id_externo = ?2")
					.setParameter(1, id).setParameter(2, idExterno).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	@Override
	public Organizacao salvar(Organizacao organizacao) {
		repository.save(organizacao);
		return organizacao;
	}

	@Override
	public Optional<Organizacao> buscarOrganizacaoPorId(OrganizacaoId id) {
		return repository.findById(id);
	}
}
