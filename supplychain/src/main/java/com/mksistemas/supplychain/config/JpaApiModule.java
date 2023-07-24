package com.mksistemas.supplychain.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

public class JpaApiModule extends Module {

	@Override
	public String getModuleName() {
		return JpaApiModule.class.getSimpleName();
	}

	@Override
	public Version version() {
		return Version.unknownVersion();
	}

	@Override
	public void setupModule(SetupContext context) {
		final SimpleSerializers serializers = new SimpleSerializers();
		serializers.addSerializer(new SliceJsonSerializer());
		context.addSerializers(serializers);
	}
}
