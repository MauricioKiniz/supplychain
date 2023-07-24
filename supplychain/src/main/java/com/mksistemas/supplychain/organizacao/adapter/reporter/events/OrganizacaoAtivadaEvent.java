package com.mksistemas.supplychain.organizacao.adapter.reporter.events;

import jakarta.annotation.Generated;

public class OrganizacaoAtivadaEvent extends OrganizacaoEvent {

	@Generated("SparkTools")
	private OrganizacaoAtivadaEvent(Builder builder) {
		this.organizacaoId = builder.organizacaoId;
		this.idExterno = builder.idExterno;
		this.nome = builder.nome;
		this.codigoPais = builder.codigoPais;
		this.identidade = builder.identidade;
		this.timeZoneInSeconds = builder.timeZoneInSeconds;
		this.ativo = builder.ativo;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String organizacaoId;
		private String idExterno;
		private String nome;
		private String codigoPais;
		private String identidade;
		private int timeZoneInSeconds;
		private boolean ativo;

		private Builder() {
		}

		public Builder withOrganizacaoId(String organizacaoId) {
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

		public Builder withAtivo(boolean ativo) {
			this.ativo = ativo;
			return this;
		}

		public OrganizacaoAtivadaEvent build() {
			return new OrganizacaoAtivadaEvent(this);
		}
	}

}
