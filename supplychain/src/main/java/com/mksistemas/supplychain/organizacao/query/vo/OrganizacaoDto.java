package com.mksistemas.supplychain.organizacao.query.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mksistemas.supplychain.library.converters.LongToTsidStringConverter;

@JsonIgnoreProperties
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class OrganizacaoDto {

	@JsonDeserialize(converter = LongToTsidStringConverter.class)
	private String organizacaoId;
	private String idExterno;
	private String nome;
	private String identidade;
	private String codigoPais;
	private int timeZoneInSeconds;
	private boolean ativo;

	public String getOrganizacaoId() {
		return organizacaoId;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public String getNome() {
		return nome;
	}

	public String getIdentidade() {
		return identidade;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public int getTimeZoneInSeconds() {
		return timeZoneInSeconds;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setOrganizacaoId(String organizacaoId) {
		this.organizacaoId = organizacaoId;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setTimeZoneInSeconds(int timeZoneInSeconds) {
		this.timeZoneInSeconds = timeZoneInSeconds;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
