package com.mksistemas.supplychain.grupoeconomico.domain;

import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.annotation.Generated;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

	@Generated("SparkTools")
	private GrupoEconomico(Builder builder) {
		this.grupoEconomicoId = builder.grupoEconomicoId;
		this.nome = builder.nome;
		this.version = builder.version;
		this.ativo = builder.ativo;
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

		public GrupoEconomico build() {
			return new GrupoEconomico(this);
		}
	}
}
