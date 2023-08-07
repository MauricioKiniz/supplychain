package com.mksistemas.supplychain.checklist.domain;

import jakarta.annotation.Generated;

public class ValorItem {
	private String nome;
	private Boolean exigeDescricao;
	private Boolean exigeDocumento;
	private int sequencia;

	@Generated("SparkTools")
	private ValorItem(Builder builder) {
		this.nome = builder.nome;
		this.exigeDescricao = builder.exigeDescricao;
		this.exigeDocumento = builder.exigeDocumento;
		this.sequencia = builder.sequencia;
	}

	public ValorItem() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getExigeDescricao() {
		return exigeDescricao;
	}

	public void setExigeDescricao(Boolean exigeDescricao) {
		this.exigeDescricao = exigeDescricao;
	}

	public Boolean getExigeDocumento() {
		return exigeDocumento;
	}

	public void setExigeDocumento(Boolean exigeDocumento) {
		this.exigeDocumento = exigeDocumento;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String nome;
		private Boolean exigeDescricao;
		private Boolean exigeDocumento;
		private int sequencia;

		private Builder() {
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withExigeDescricao(Boolean exigeDescricao) {
			this.exigeDescricao = exigeDescricao;
			return this;
		}

		public Builder withExigeDocumento(Boolean exigeDocumento) {
			this.exigeDocumento = exigeDocumento;
			return this;
		}

		public Builder withSequencia(int sequencia) {
			this.sequencia = sequencia;
			return this;
		}

		public ValorItem build() {
			return new ValorItem(this);
		}
	}

}
