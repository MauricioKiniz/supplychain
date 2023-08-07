package com.mksistemas.supplychain.checklist.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.annotation.Generated;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity(name = "checklists")
public class Checklist extends AbstractAggregateRoot<Checklist> {

	public Checklist() {
	}

	@EmbeddedId()
	@AttributeOverride(name = "id", column = @Column(name = "checklist_id"))
	private ChecklistId checklistId;

	@Column(name = "nome", length = 250, nullable = false)
	private String nome;

	@Version
	private Integer version;

	@Column(name = "ativo", length = 100, nullable = false)
	private boolean ativo = true;

	@Column(name = "descricao", length = 4000, nullable = true)
	private String descricao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checklist")
	private Set<ChecklistItem> orderDetail = new HashSet<>();

	@Generated("SparkTools")
	private Checklist(Builder builder) {
		this.checklistId = builder.checklistId;
		this.nome = builder.nome;
		this.version = builder.version;
		this.ativo = builder.ativo;
		this.descricao = builder.descricao;
	}

	public ChecklistId getChecklistId() {
		return checklistId;
	}

	public void setChecklistId(ChecklistId checklistId) {
		this.checklistId = checklistId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private ChecklistId checklistId;
		private String nome;
		private Integer version;
		private boolean ativo = true;
		private String descricao;

		private Builder() {
		}

		public Builder withChecklistId(ChecklistId checklistId) {
			this.checklistId = checklistId;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
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

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public Checklist build() {
			return new Checklist(this);
		}
	}

}
