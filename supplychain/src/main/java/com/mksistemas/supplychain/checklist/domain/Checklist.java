package com.mksistemas.supplychain.checklist.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.annotation.Generated;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checklist", cascade = CascadeType.PERSIST)
	private List<ChecklistItem> items = new ArrayList<>();

	public void adicionarItem(ChecklistItem item) {
		items.add(item);
	}

	public boolean alterarItem(ChecklistItem item) {
		int posicao = items.indexOf(item);
		if (posicao >= 0)
			items.set(posicao, item);
		return posicao >= 0;
	}

	public boolean removerItemPorId(ChecklistItemId id) {
		Optional<ChecklistItem> item = items.stream().filter(dt -> dt.getChecklistItemId().equals(id)).findFirst();
		if (item.isPresent())
			items.remove(item.get());
		return item.isPresent();
	}

	@Generated("SparkTools")
	private Checklist(Builder builder) {
		this.checklistId = builder.checklistId;
		this.nome = builder.nome;
		this.version = builder.version;
		this.ativo = builder.ativo;
		this.descricao = builder.descricao;
		this.items = builder.items;
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

	public List<ChecklistItem> getItems() {
		return items;
	}

	public void setItems(List<ChecklistItem> items) {
		this.items = items;
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
		private List<ChecklistItem> items = new ArrayList<>();

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

		public Builder withItems(List<ChecklistItem> items) {
			this.items = items;
			return this;
		}

		public Checklist build() {
			return new Checklist(this);
		}
	}

}
