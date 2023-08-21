package com.mksistemas.supplychain.checklist.application.vo;

import com.mksistemas.supplychain.checklist.ChecklistMessages;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotBlank;

public class ChecklistValorDto {
	private ExigeInformacaoEnum exigeDescricao;
	private ExigeInformacaoEnum exigeDocumento;
	@NotBlank(message = ChecklistMessages.NOME_VALOR_BLANK)
	private String nome;
	private int sequencia = 0;

	@Generated("SparkTools")
	private ChecklistValorDto(Builder builder) {
		this.exigeDescricao = builder.exigeDescricao;
		this.exigeDocumento = builder.exigeDocumento;
		this.nome = builder.nome;
		this.sequencia = builder.sequencia;
	}

	public ChecklistValorDto() {
	}

	public ExigeInformacaoEnum getExigeDescricao() {
		return exigeDescricao;
	}

	public void setExigeDescricao(ExigeInformacaoEnum exigeDescricao) {
		this.exigeDescricao = exigeDescricao;
	}

	public ExigeInformacaoEnum getExigeDocumento() {
		return exigeDocumento;
	}

	public void setExigeDocumento(ExigeInformacaoEnum exigeDocumento) {
		this.exigeDocumento = exigeDocumento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		private ExigeInformacaoEnum exigeDescricao;
		private ExigeInformacaoEnum exigeDocumento;
		private String nome;
		private int sequencia;

		private Builder() {
		}

		public Builder withExigeDescricao(ExigeInformacaoEnum exigeDescricao) {
			this.exigeDescricao = exigeDescricao;
			return this;
		}

		public Builder withExigeDocumento(ExigeInformacaoEnum exigeDocumento) {
			this.exigeDocumento = exigeDocumento;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withSequencia(int sequencia) {
			this.sequencia = sequencia;
			return this;
		}

		public ChecklistValorDto build() {
			return new ChecklistValorDto(this);
		}
	}

}
