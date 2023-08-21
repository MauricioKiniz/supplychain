package com.mksistemas.supplychain.checklist.adapter.reporter.events;

import jakarta.annotation.Generated;

public class ChecklistCriadoEvent extends BaseChecklistEvent {

	@Generated("SparkTools")
	private ChecklistCriadoEvent(Builder builder) {
		this.checklistId = builder.checklistId;
		this.nome = builder.nome;
		this.descricao = builder.descricao;
	}

	public ChecklistCriadoEvent() {
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String checklistId;
		private String nome;
		private String descricao;

		private Builder() {
		}

		public Builder withChecklistId(String checklistId) {
			this.checklistId = checklistId;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public ChecklistCriadoEvent build() {
			return new ChecklistCriadoEvent(this);
		}
	}

}
