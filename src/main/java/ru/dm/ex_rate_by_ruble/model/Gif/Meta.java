package ru.dm.ex_rate_by_ruble.model.Gif;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public
class Meta {
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String response_id;
}
