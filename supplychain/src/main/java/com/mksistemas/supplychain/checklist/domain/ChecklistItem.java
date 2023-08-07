package com.mksistemas.supplychain.checklist.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.annotation.Generated;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

@Entity(name = "checklistitems")
public class ChecklistItem extends AbstractAggregateRoot<ChecklistItem> {

	public ChecklistItem() {
	}

	@EmbeddedId()
	@AttributeOverride(name = "id", column = @Column(name = "checklist_item_id"))
	private ChecklistId checklistItemId;

	@Column(name = "nome", length = 250, nullable = false)
	private String nome;

	@Version
	private Integer version;

	@Column(name = "descricao", length = 4000, nullable = true)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_id")
	private Checklist checklist;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "valores", nullable = true)
	private List<ValorItem> valores = new ArrayList<>();

	@Generated("SparkTools")
	private ChecklistItem(Builder builder) {
		this.checklistItemId = builder.checklistItemId;
		this.nome = builder.nome;
		this.version = builder.version;
		this.descricao = builder.descricao;
	}

	public ChecklistId getChecklistItemId() {
		return checklistItemId;
	}

	public void setChecklistItemId(ChecklistId checklistItemId) {
		this.checklistItemId = checklistItemId;
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
		private ChecklistId checklistItemId;
		private String nome;
		private Integer version;
		private String descricao;

		private Builder() {
		}

		public Builder withChecklistItemId(ChecklistId checklistItemId) {
			this.checklistItemId = checklistItemId;
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

		public Builder withDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public ChecklistItem build() {
			return new ChecklistItem(this);
		}
	}

}
