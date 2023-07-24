package com.mksistemas.supplychain.grupoeconomico.domain;

import java.io.Serializable;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;
import com.mksistemas.supplychain.library.entities.BaseEntityId;

public class GrupoEconomicoId extends BaseEntityId implements Serializable {

	private static final long serialVersionUID = -750572359459251741L;

	public GrupoEconomicoId() {
	}

	public GrupoEconomicoId(Tsid id) {
		super(id);
	}

	public static GrupoEconomicoId generate() {
		return new GrupoEconomicoId(TsidCreator.getTsid256());
	}

	public static GrupoEconomicoId from(String id) {
		return new GrupoEconomicoId(Tsid.from(id));
	}
}
