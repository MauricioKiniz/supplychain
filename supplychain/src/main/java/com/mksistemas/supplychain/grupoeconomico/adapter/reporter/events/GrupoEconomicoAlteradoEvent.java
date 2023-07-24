package com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events;

import jakarta.annotation.Generated;

public class GrupoEconomicoAlteradoEvent extends BaseGrupoEconomicoEvent {

	@Generated("SparkTools")
	private GrupoEconomicoAlteradoEvent(Builder builder) {
		this.grupoEconomicoId = builder.grupoEconomicoId;
		this.nome = builder.nome;
		this.ativo = builder.ativo;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String grupoEconomicoId;
		private String nome;
		private boolean ativo;

		private Builder() {
		}

		public Builder withGrupoEconomicoId(String grupoEconomicoId) {
			this.grupoEconomicoId = grupoEconomicoId;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withAtivo(boolean ativo) {
			this.ativo = ativo;
			return this;
		}

		public GrupoEconomicoAlteradoEvent build() {
			return new GrupoEconomicoAlteradoEvent(this);
		}
	}

}
