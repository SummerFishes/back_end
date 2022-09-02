package com.example.stocktrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.example.stocktrade.feign"})
public class StockTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockTradeApplication.class, args);
    }

}
