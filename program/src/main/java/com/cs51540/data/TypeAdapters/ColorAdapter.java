package com.cs51540.data.TypeAdapters;

import java.awt.Color;
import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class ColorAdapter extends TypeAdapter<Color> {
    @Override
    public Color read(JsonReader reader) throws IOException {
        int red = 0;
        int green = 0;
        int blue = 0;
        int alpha = 255; // Default alpha value

        reader.beginObject();
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                String fieldName = reader.nextName();
                switch (fieldName) {
                    case "red":
                        red = reader.nextInt();
                        break;
                    case "green":
                        green = reader.nextInt();
                        break;
                    case "blue":
                        blue = reader.nextInt();
                        break;
                    case "alpha":
                        alpha = reader.nextInt();
                        break;
                    default:
                        reader.skipValue(); // Ignore unknown fields
                        break;
                }
            }
        }
        reader.endObject();
        return new Color(red, green, blue, alpha);
    }

    @Override
    public void write(JsonWriter writer, Color color) throws IOException {
        writer.beginObject();
        writer.name("red").value(color.getRed());
        writer.name("green").value(color.getGreen());
        writer.name("blue").value(color.getBlue());
        writer.name("alpha").value(color.getAlpha());
        writer.endObject();
    }
}