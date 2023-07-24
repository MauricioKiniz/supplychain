package com.mksistemas.supplychain.organizacao.domain;

import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.annotation.Generated;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

@Entity(name = "organizacoes")
public class Organizacao extends AbstractAggregateRoot<Organizacao> {

	@EmbeddedId()
	@AttributeOverride(name = "id", column = @Column(name = "organizacao_id"))
	private OrganizacaoId organizacaoId;

	@Column(name = "id_externo", length = 150, nullable = false, unique = true)
	private String idExterno;
	@Column(name = "nome", length = 250, nullable = false)
	private String nome;
	@Column(name = "codigo_pais", length = 3, nullable = false)
	private String codigoPais;
	@Column(name = "identidade", length = 100, nullable = false)
	private String identidade;
	private int timeZoneInSeconds;
	@Version
	private Integer version;
	@Column(name = "ativo", length = 100, nullable = false)
	private boolean ativo = true;

	public Organizacao() {
	}

	@Generated("SparkTools")
	private Organizacao(Builder builder) {
		this.organizacaoId = builder.organizacaoId;
		this.idExterno = builder.idExterno;
		this.nome = builder.nome;
		this.codigoPais = builder.codigoPais;
		this.identidade = builder.identidade;
		this.timeZoneInSeconds = builder.timeZoneInSeconds;
		this.version = builder.version;
		this.ativo = builder.ativo;
	}

	public OrganizacaoId getOrganizacaoId() {
		return organizacaoId;
	}

	public void setOrganizacaoId(OrganizacaoId organizacaoId) {
		this.organizacaoId = organizacaoId;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public int getTimeZoneInSeconds() {
		return timeZoneInSeconds;
	}

	public void setTimeZoneInSeconds(int timeZone) {
		this.timeZoneInSeconds = timeZone;
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

	public void setAtivo(boolean active) {
		this.ativo = active;
	}

	public void ativar() {
		this.ativo = true;
	}

	public void desativar() {
		this.ativo = false;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private OrganizacaoId organizacaoId;
		private String idExterno;
		private String nome;
		private String codigoPais;
		private String identidade;
		private int timeZoneInSeconds;
		private Integer version;
		private boolean ativo = true;

		private Builder() {
		}

		public Builder withOrganizacaoId(OrganizacaoId organizacaoId) {
			this.organizacaoId = organizacaoId;
			return this;
		}

		public Builder withIdExterno(String idExterno) {
			this.idExterno = idExterno;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withCodigoPais(String codigoPais) {
			this.codigoPais = codigoPais;
			return this;
		}

		public Builder withIdentidade(String identidade) {
			this.identidade = identidade;
			return this;
		}

		public Builder withTimeZoneInSeconds(int timeZoneInSeconds) {
			this.timeZoneInSeconds = timeZoneInSeconds;
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

		public Organizacao build() {
			return new Organizacao(this);
		}
	}

}
