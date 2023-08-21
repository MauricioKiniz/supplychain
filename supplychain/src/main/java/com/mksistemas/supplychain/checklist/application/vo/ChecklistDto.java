package com.mksistemas.supplychain.checklist.application.vo;

import java.util.ArrayList;
import java.util.List;

import com.mksistemas.supplychain.checklist.ChecklistMessages;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotBlank;

public class ChecklistDto {
	@NotBlank(message = ChecklistMessages.NOME_BLANK)
	protected String nome;
	protected String descricao;
	protected List<ChecklistItemDto> items = new ArrayList<>();

	@Generated("SparkTools")
	private ChecklistDto(Builder builder) {
		this.nome = builder.nome;
		this.descricao = builder.descricao;
		this.items = builder.items;
	}

	public ChecklistDto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ChecklistItemDto> getItems() {
		return items;
	}

	public void setItems(List<ChecklistItemDto> items) {
		this.items = items;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String nome;
		private String descricao;
		private List<ChecklistItemDto> items = new ArrayList<>();

		private Builder() {
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public Builder withItems(List<ChecklistItemDto> items) {
			this.items = items;
			return this;
		}

		public ChecklistDto build() {
			return new ChecklistDto(this);
		}
	}

}
