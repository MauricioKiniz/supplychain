package com.mksistemas.supplychain.library.converters;

import java.io.Serializable;
import java.util.Objects;

import com.github.f4b6a3.tsid.Tsid;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;

public class TsidIdKey implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TsidIdKey(Tsid id) {
        this.id = id;
    }

    public TsidIdKey() {}

    @Convert(converter = TsidConverter.class)
    @Column(name = "ID")
    private Tsid id;

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
        TsidIdKey other = (TsidIdKey) obj;
        return Objects.equals(id, other.id);
    }

}
