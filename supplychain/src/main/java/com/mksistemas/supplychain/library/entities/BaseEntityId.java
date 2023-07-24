package com.mksistemas.supplychain.library.entities;

import java.util.Objects;

import com.github.f4b6a3.tsid.Tsid;
import com.mksistemas.supplychain.library.converters.TsidConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntityId {

	@Convert(converter = TsidConverter.class)
	private Tsid id;

	protected BaseEntityId() {
	}

	protected BaseEntityId(Tsid id) {
		this.id = id;
	}

	public Tsid getId() {
		return id;
	}

	public void setId(Tsid id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntityId other = (BaseEntityId) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return id.toString();
	}

	public String toLowerCase() {
		return id.toLowerCase();
	}

}
