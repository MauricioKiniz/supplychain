package com.mksistemas.supplychain.config;

import java.io.IOException;

import org.springframework.data.domain.Slice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class SliceJsonSerializer extends StdSerializer<Slice<?>> {

	private static final long serialVersionUID = -7903943382132407549L;

	protected SliceJsonSerializer() {
		super(Slice.class, false);
	}

	@Override
		public void serialize(Slice<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

			gen.writeStartObject();
			gen.writeFieldName("items");
			gen.writeStartArray();

			for (Object item : value) {
				gen.writeObject(item);
			}

			gen.writeEndArray();
			gen.writeNumberField("totalElementos", value.getNumberOfElements());
			gen.writeNumberField("pagina", value.getPageable().getPageNumber());
			gen.writeBooleanField("proximo", value.hasNext());
			gen.writeEndObject();
		}
	}
