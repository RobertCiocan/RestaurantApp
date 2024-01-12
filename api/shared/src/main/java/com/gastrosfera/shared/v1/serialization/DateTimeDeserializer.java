package com.gastrosfera.shared.v1.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gastrosfera.shared.v1.exception._4xx.InvalidDataTypeException;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;

public class DateTimeDeserializer extends StdDeserializer<Instant> {

    protected DateTimeDeserializer(Class<Instant> t) {
        super(t);
    }


    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String date = jsonParser.getText();
        try {
            return date == null ? null : Instant.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDataTypeException("Invalid date", e);
        }
    }
}
