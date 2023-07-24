package com.mksistemas.supplychain.grupoeconomico.adapter.reporter.events;

public abstract class BaseGrupoEconomicoEvent {
	protected String grupoEconomicoId;
	protected String nome;
	protected boolean ativo;

	public String getGrupoEconomicoId() {
		return grupoEconomicoId;
	}

	public void setGrupoEconomicoId(String grupoEconomicoId) {
		this.grupoEconomicoId = grupoEconomicoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
