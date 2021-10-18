package ru.dm.ex_rate_by_ruble.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dm.ex_rate_by_ruble.model.Gif.GifResponse;

@FeignClient(value = "giphy", url = "${url.giphy.api}")
public interface GiphyService {

    String api_key = "Sf2UFy0j3oDZkc8BGZdI6XxYgZJS2qbs";

    @GetMapping("?api_key=" + "${key.giphy.api}" + "&tag=rich")
    GifResponse getRich();

    @GetMapping("?api_key=" + "${key.giphy.api}" + "&tag=broke")
    GifResponse getBroke();
}
