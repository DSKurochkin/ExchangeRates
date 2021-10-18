package ru.dm.ex_rate_by_ruble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExRateByRubleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExRateByRubleApplication.class, args);
    }

}
