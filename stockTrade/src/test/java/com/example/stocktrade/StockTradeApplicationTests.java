package com.example.stocktrade;

import com.example.stocktrade.service.ShowTableService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class StockTradeApplicationTests {
    @Resource
    ShowTableService showTableService;

    @Test
    void contextLoads() {
        System.out.println(showTableService.showTable("1W").toString());;
    }

}
