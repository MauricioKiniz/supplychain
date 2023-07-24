package com.mksistemas.supplychain.library.validators.tsid;

import org.apache.commons.lang3.StringUtils;

import com.github.f4b6a3.tsid.Tsid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TsidIdValidator implements ConstraintValidator<TsidId, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			if (StringUtils.isNotBlank(value))
				Tsid.from(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
