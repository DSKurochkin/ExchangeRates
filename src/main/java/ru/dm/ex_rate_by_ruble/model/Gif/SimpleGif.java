package ru.dm.ex_rate_by_ruble.model.Gif;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dm.ex_rate_by_ruble.json.SimpleGifDeserializer;

@JsonDeserialize(using = SimpleGifDeserializer.class)
@Data
@AllArgsConstructor
public class SimpleGif {
    private String type;
    private String id;
    private String url;
    private String bitly_url;
    private String embed_url;
    private String title;
    private String username;
    private String rating;
    private String import_datetime;
    private String image_original_url;
    private String image_url;
    private String image_mp4_url;
    private String image_frames;
    private String image_width;
    private String image_height;

}
