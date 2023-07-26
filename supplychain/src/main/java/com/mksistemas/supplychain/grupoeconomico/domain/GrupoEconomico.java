package com.mksistemas.supplychain.grupoeconomico.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.annotation.Generated;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Version;

@Entity(name = "gruposeconomicos")
public class GrupoEconomico extends AbstractAggregateRoot<GrupoEconomico> {

	public GrupoEconomico() {
	}

	@EmbeddedId()
	@AttributeOverride(name = "id", column = @Column(name = "grupo_economico_id"))
	private GrupoEconomicoId grupoEconomicoId;

	@Column(name = "nome", length = 250, nullable = false)
	private String nome;
	@Version
	private Integer version;
	@Column(name = "ativo", length = 100, nullable = false)
	private boolean ativo = true;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "organizacoes_grupoeconomico", joinColumns = @JoinColumn(name = "grupo_economico_id"))
	@Column(name = "organizacao_id", nullable = false, length = 50)
	private List<String> organizacoes = new ArrayList<>();

	@Generated("SparkTools")
	private GrupoEconomico(Builder builder) {
		this.grupoEconomicoId = builder.grupoEconomicoId;
		this.nome = builder.nome;
		this.version = builder.version;
		this.ativo = builder.ativo;
		this.organizacoes = builder.organizacoes;
	}

	public GrupoEconomicoId getGrupoEconomicoId() {
		return grupoEconomicoId;
	}

	public void setGrupoEconomicoId(GrupoEconomicoId grupoEconomicoId) {
		this.grupoEconomicoId = grupoEconomicoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<String> getOrganizacoes() {
		return organizacoes;
	}

	public void setOrganizacoes(List<String> organizacoes) {
		this.organizacoes = organizacoes;
	}

	public void ativar() {
		this.ativo = true;
	}

	public void desativar() {
		this.ativo = false;
	}

	public boolean organizacaoVinculada(String organizacaoId) {
		return organizacoes.contains(organizacaoId);
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private GrupoEconomicoId grupoEconomicoId;
		private String nome;
		private Integer version;
		private boolean ativo = true;
		private List<String> organizacoes = new ArrayList<>();

		private Builder() {
		}

		public Builder withGrupoEconomicoId(GrupoEconomicoId grupoEconomicoId) {
			this.grupoEconomicoId = grupoEconomicoId;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withVersion(Integer version) {
			this.version = version;
			return this;
		}

		public Builder withAtivo(boolean ativo) {
			this.ativo = ativo;
			return this;
		}

		public Builder withOrganizacoes(List<String> organizacoes) {
			this.organizacoes = organizacoes;
			return this;
		}

		public GrupoEconomico build() {
			return new GrupoEconomico(this);
		}
	}

}
