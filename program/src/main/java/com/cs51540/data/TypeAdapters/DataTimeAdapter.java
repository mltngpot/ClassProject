package com.cs51540.data.TypeAdapters;

import com.google.gson.TypeAdapter;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class DataTimeAdapter extends TypeAdapter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public LocalDateTime read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
    
        String dateTimeString = reader.nextString();
            return LocalDateTime.parse(dateTimeString, FORMATTER);
        }
    @Override
    public void write(JsonWriter writer, LocalDateTime dateTime) throws IOException {
        if (dateTime == null) {
            writer.nullValue();
            return;
        }
    
        String dateTimeString = dateTime.format(FORMATTER);
        writer.value(dateTimeString);
    }
}
    
