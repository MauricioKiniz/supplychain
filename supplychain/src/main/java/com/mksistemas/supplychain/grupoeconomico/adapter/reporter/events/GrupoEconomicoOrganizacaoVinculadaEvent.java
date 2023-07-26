package com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events;

import java.util.Collections;
import java.util.List;

import jakarta.annotation.Generated;

public class GrupoEconomicoOrganizacaoVinculadaEvent {
	private String grupoEconomicoId;
	private List<String> organizacoesVinculadas;

	public GrupoEconomicoOrganizacaoVinculadaEvent() {
	}

	@Generated("SparkTools")
	private GrupoEconomicoOrganizacaoVinculadaEvent(Builder builder) {
		this.grupoEconomicoId = builder.grupoEconomicoId;
		this.organizacoesVinculadas = builder.organizacoesVinculadas;
	}

	public String getGrupoEconomicoId() {
		return grupoEconomicoId;
	}

	public void setGrupoEconomicoId(String grupoEconomicoId) {
		this.grupoEconomicoId = grupoEconomicoId;
	}

	public List<String> getOrganizacoesVinculadas() {
		return organizacoesVinculadas;
	}

	public void setOrganizacoesVinculadas(List<String> organizacoesVinculadas) {
		this.organizacoesVinculadas = organizacoesVinculadas;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String grupoEconomicoId;
		private List<String> organizacoesVinculadas = Collections.emptyList();

		private Builder() {
		}

		public Builder withGrupoEconomicoId(String grupoEconomicoId) {
			this.grupoEconomicoId = grupoEconomicoId;
			return this;
		}

		public Builder withOrganizacoesVinculadas(List<String> organizacoesVinculadas) {
			this.organizacoesVinculadas = organizacoesVinculadas;
			return this;
		}

		public GrupoEconomicoOrganizacaoVinculadaEvent build() {
			return new GrupoEconomicoOrganizacaoVinculadaEvent(this);
		}
	}

}
