package com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events;

import java.util.Collections;
import java.util.List;

import jakarta.annotation.Generated;

public class GrupoEconomicoOrganizacaoDesvinculadaEvent {
	private String grupoEconomicoId;
	private List<String> organizacoesDesvinculadas;

	@Generated("SparkTools")
	private GrupoEconomicoOrganizacaoDesvinculadaEvent(Builder builder) {
		this.grupoEconomicoId = builder.grupoEconomicoId;
		this.organizacoesDesvinculadas = builder.organizacoesDesvinculadas;
	}

	public GrupoEconomicoOrganizacaoDesvinculadaEvent() {
	}

	public String getGrupoEconomicoId() {
		return grupoEconomicoId;
	}

	public void setGrupoEconomicoId(String grupoEconomicoId) {
		this.grupoEconomicoId = grupoEconomicoId;
	}

	public List<String> getOrganizacoesVinculadas() {
		return organizacoesDesvinculadas;
	}

	public void setOrganizacoesVinculadas(List<String> organizacoesVinculadas) {
		this.organizacoesDesvinculadas = organizacoesVinculadas;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String grupoEconomicoId;
		private List<String> organizacoesDesvinculadas = Collections.emptyList();

		private Builder() {
		}

		public Builder withGrupoEconomicoId(String grupoEconomicoId) {
			this.grupoEconomicoId = grupoEconomicoId;
			return this;
		}

		public Builder withOrganizacoesDesvinculadas(List<String> organizacoesDesvinculadas) {
			this.organizacoesDesvinculadas = organizacoesDesvinculadas;
			return this;
		}

		public GrupoEconomicoOrganizacaoDesvinculadaEvent build() {
			return new GrupoEconomicoOrganizacaoDesvinculadaEvent(this);
		}
	}

}
