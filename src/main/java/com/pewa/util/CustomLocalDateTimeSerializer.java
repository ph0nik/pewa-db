package com.pewa.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by phonik on 2017-04-08.
 *
 * Wzorzec daty wg ISO 8601, uwzględnia tysięczne sekundy, łatwy do odczytu przez JavaScript
 *
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Override
    public void serialize(LocalDateTime date, JsonGenerator generator,
                          SerializerProvider provider) throws IOException, JsonProcessingException {

        String dateString = date.format(formatter);
        generator.writeString(dateString);
    }
}
