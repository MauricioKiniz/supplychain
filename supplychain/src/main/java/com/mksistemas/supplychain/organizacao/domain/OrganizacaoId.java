package com.mksistemas.supplychain.organizacao.domain;

import java.io.Serializable;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;
import com.mksistemas.supplychain.library.entities.BaseEntityId;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrganizacaoId extends BaseEntityId implements Serializable {

	private static final long serialVersionUID = 2601952888557045467L;

	public OrganizacaoId() {
	}

	public OrganizacaoId(Tsid id) {
		super(id);
	}

	public static OrganizacaoId generate() {
		return new OrganizacaoId(TsidCreator.getTsid256());
	}

	public static OrganizacaoId from(String id) {
		return new OrganizacaoId(Tsid.from(id));
	}
}
