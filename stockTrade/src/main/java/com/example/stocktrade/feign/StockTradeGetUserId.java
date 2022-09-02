package com.example.stocktrade.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userClientService")
public interface StockTradeGetUserId {

    @PostMapping("searchUser")
    int searchUser(@RequestBody String userName);
}
