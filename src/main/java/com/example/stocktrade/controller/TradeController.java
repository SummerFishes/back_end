package com.example.stocktrade.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/trade")
public class TradeController {
    @Resource
    TradeService tradeService;
    @Resource
    ShowTableService showTableService;

    @PostMapping("/buy_sell")
    @CrossOrigin
    public JSONObject addTrade(@RequestBody JSONObject jsonObject) {
        System.out.println("接收到数据："+jsonObject.toString());
        //首先判断用户，股票，机构，销售员是否存在
        String clientName = jsonObject.getString("clientName");
        String ticker = jsonObject.getString("ticker");
        String sector = jsonObject.getString("sector");
        String salesperson = jsonObject.getString("salesperson");
        String ric = jsonObject.getString("ric");
        int size = jsonObject.getInteger("size");
        float price = jsonObject.getFloat("price");
        String currency = jsonObject.getString("currency");
        int hp = jsonObject.getString("hp").equals("HT") ? 0 : 1;
        int flag = jsonObject.getInteger("flag");
        //存储返回结果
        String result = tradeService.trade(clientName, ticker, sector, salesperson,
                ric,size, price, currency, hp, flag);
        JSONObject res = new JSONObject();
        res.put("result",result);
        return res;
    }

    @PostMapping("/filter")
    @CrossOrigin
    public JSONObject showTable(@RequestBody JSONObject jsonObject) {
        String gap = jsonObject.getString("gap");
        JSONObject res = showTableService.showTable(gap);
        return res;
    }
}
