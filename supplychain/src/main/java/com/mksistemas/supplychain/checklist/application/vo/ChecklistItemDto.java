package com.mksistemas.supplychain.checklist.application.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mksistemas.supplychain.checklist.ChecklistMessages;
import com.mksistemas.supplychain.checklist.domain.ChecklistItem;
import com.mksistemas.supplychain.checklist.domain.ChecklistItemId;
import com.mksistemas.supplychain.checklist.domain.ValorItem;
import com.mksistemas.supplychain.checklist.domain.ValorItemEnum;

import jakarta.annotation.Generated;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class ChecklistItemDto {
	@NotBlank(message = ChecklistMessages.NOME_ITEM_BLANK)
	private String nome;
	private String descricao;
	private List<ChecklistValorDto> valores = new ArrayList<>();

	@Generated("SparkTools")
	private ChecklistItemDto(Builder builder) {
		this.nome = builder.nome;
		this.descricao = builder.descricao;
		this.valores = builder.valores;
	}

	public ChecklistItemDto() {
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

	public List<ChecklistValorDto> getValores() {
		return valores;
	}

	public void setValores(List<ChecklistValorDto> valores) {
		this.valores = valores;
	}

	private ValorItemEnum getValorItemEnumeracao(ExigeInformacaoEnum exige) {
		switch (exige) {
		case NAONECESSARIO:
			return ValorItemEnum.NAONECESSARIO;
		case OPCIONAL:
			return ValorItemEnum.OPCIONAL;
		case OBRIGATORIO:
			return ValorItemEnum.OBRIGATORIO;
		default:
			return ValorItemEnum.NAONECESSARIO;
		}
	}

	private ValorItem montarValor(ChecklistValorDto valorItem) {
		return ValorItem.builder().withExigeDescricao(getValorItemEnumeracao(valorItem.getExigeDescricao()))
				.withExigeDocumento(getValorItemEnumeracao(valorItem.getExigeDocumento())).withNome(valorItem.getNome())
				.withSequencia(valorItem.getSequencia()).build();
	}

	public ChecklistItem montarChecklistItemEntity(@Nullable ChecklistItemId id) {
		List<ValorItem> valoresGerados = valores.stream()
				.collect(Collectors.mapping(this::montarValor, Collectors.toList()));
		return ChecklistItem.builder().withChecklistItemId(id).withDescricao(descricao).withNome(nome)
				.withValores(valoresGerados).build();
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String nome;
		private String descricao;
		private List<ChecklistValorDto> valores = new ArrayList<>();

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

		public Builder withValores(List<ChecklistValorDto> valores) {
			this.valores = valores;
			return this;
		}

		public ChecklistItemDto build() {
			return new ChecklistItemDto(this);
		}
	}
}
