package com.mksistemas.supplychain.checklist.domain;

import jakarta.annotation.Generated;

public class ValorItem {
	private String nome;
	private ValorItemEnum exigeDescricao;
	private ValorItemEnum exigeDocumento;
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

	public ValorItemEnum getExigeDescricao() {
		return exigeDescricao;
	}

	public void setExigeDescricao(ValorItemEnum exigeDescricao) {
		this.exigeDescricao = exigeDescricao;
	}

	public ValorItemEnum getExigeDocumento() {
		return exigeDocumento;
	}

	public void setExigeDocumento(ValorItemEnum exigeDocumento) {
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
		private ValorItemEnum exigeDescricao;
		private ValorItemEnum exigeDocumento;
		private int sequencia;

		private Builder() {
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withExigeDescricao(ValorItemEnum exigeDescricao) {
			this.exigeDescricao = exigeDescricao;
			return this;
		}

		public Builder withExigeDocumento(ValorItemEnum exigeDocumento) {
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
