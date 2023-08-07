package com.mksistemas.supplychain.checklist.domain;

import java.io.Serializable;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;
import com.mksistemas.supplychain.library.entities.BaseEntityId;

public class ChecklistId extends BaseEntityId implements Serializable {

	private static final long serialVersionUID = 3939347209866984711L;

	public ChecklistId() {
	}

	public ChecklistId(Tsid id) {
		super(id);
	}

	public static ChecklistId generate() {
		return new ChecklistId(TsidCreator.getTsid256());
	}

	public static ChecklistId from(String id) {
		return new ChecklistId(Tsid.from(id));
	}

}
