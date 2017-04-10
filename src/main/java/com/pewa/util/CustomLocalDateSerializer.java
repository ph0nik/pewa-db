package com.pewa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by phonik on 2017-04-10.
 */
public class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T00:00:00.000Z'");

        @Override
        public void serialize(LocalDate date, JsonGenerator generator,
                              SerializerProvider provider) throws IOException, JsonProcessingException {

            String dateString = date.format(formatter);
            generator.writeString(dateString);
        }

}
