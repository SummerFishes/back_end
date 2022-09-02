package com.example.stocktrade;

import com.example.stocktrade.service.ShowTableService;
import com.example.stocktrade.service.Impl.AboutRateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class StockTradeApplicationTests {
    @Resource
    ShowTableService showTableService;
    @Resource
    AboutRateServiceImpl getRate;

    @Test
    void contextLoads() {

//        try {
//            getRate.updateRate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(getRate.calculate("USD"));
    }

}
