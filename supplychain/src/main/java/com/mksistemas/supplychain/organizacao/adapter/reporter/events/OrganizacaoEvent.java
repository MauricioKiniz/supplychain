package com.mksistemas.supplychain.organizacao.adapter.reporter.events;

public abstract class OrganizacaoEvent {
	protected String organizacaoId;
	protected String idExterno;
	protected String nome;
	protected String codigoPais;
	protected String identidade;
	protected int timeZoneInSeconds;
	protected boolean ativo;

	public String getOrganizacaoId() {
		return organizacaoId;
	}

	public void setOrganizacaoId(String organizacaoId) {
		this.organizacaoId = organizacaoId;
	}

	public String getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public int getTimeZoneInSeconds() {
		return timeZoneInSeconds;
	}

	public void setTimeZoneInSeconds(int timeZoneInSeconds) {
		this.timeZoneInSeconds = timeZoneInSeconds;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
