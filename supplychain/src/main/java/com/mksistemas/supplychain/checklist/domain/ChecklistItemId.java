package com.mksistemas.supplychain.checklist.domain;

import java.io.Serializable;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;
import com.mksistemas.supplychain.library.entities.BaseEntityId;

public class ChecklistItemId extends BaseEntityId implements Serializable {

	private static final long serialVersionUID = 3939347209866984712L;

	public ChecklistItemId() {
	}

	public ChecklistItemId(Tsid id) {
		super(id);
	}

	public static ChecklistItemId generate() {
		return new ChecklistItemId(TsidCreator.getTsid256());
	}

	public static ChecklistItemId from(String id) {
		return new ChecklistItemId(Tsid.from(id));
	}

}
