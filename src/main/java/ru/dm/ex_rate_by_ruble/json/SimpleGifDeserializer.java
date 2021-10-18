package ru.dm.ex_rate_by_ruble.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.dm.ex_rate_by_ruble.model.Gif.SimpleGif;

import java.io.IOException;

public class SimpleGifDeserializer extends JsonDeserializer<SimpleGif> {

    @Override
    public SimpleGif deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return new SimpleGif(node.get("type").asText()
                , node.get("id").asText()
                , node.get("url").asText()
                , node.get("bitly_url").asText()
                , node.get("embed_url").asText()
                , node.get("title").asText()
                , node.get("username").asText()
                , node.get("rating").asText()
                , node.get("import_datetime").asText()
                , node.get("image_original_url").asText()
                , node.get("image_url").asText()
                , node.get("image_mp4_url").asText()
                , node.get("image_frames").asText()
                , node.get("image_width").asText()
                , node.get("image_height").asText());
    }


}