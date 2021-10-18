package ru.dm.ex_rate_by_ruble.model.Gif;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class GifResponse {
    @Getter
    @Setter
    private SimpleGif data;
    @Getter
    @Setter
    private Meta meta;

}

