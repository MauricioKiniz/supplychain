package com.mksistemas.supplychain.library.converters;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.f4b6a3.tsid.Tsid;

public class LongToTsidStringConverter extends StdConverter<Long, String> {

	@Override
	public String convert(Long value) {
		return Tsid.from(value).toLowerCase();
	}
}
