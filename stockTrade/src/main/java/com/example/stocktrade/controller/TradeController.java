package com.example.stocktrade.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.service.ShowGraphService;
import com.example.stocktrade.service.ShowTableService;
import com.example.stocktrade.service.TradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/trade")
public class TradeController {
    @Resource
    TradeService tradeService;
    @Resource
    ShowTableService showTableService;
    @Resource
    ShowGraphService showGraphService;

    @PostMapping("/addTrade")
    @CrossOrigin
    public JSONObject addTrade(@RequestBody JSONObject jsonObject) {
        System.out.println("接收到数据：" + jsonObject.toString());
        //首先判断用户，股票，机构，销售员是否存在
        String clientName = jsonObject.getString("clientName");
        String ticker = jsonObject.getString("ticker");
        String sector = jsonObject.getString("sector");
        String salesperson = jsonObject.getString("salesperson");
        String ric = jsonObject.getString("ric");
        String size = jsonObject.getString("size");
        String price = jsonObject.getString("price");
        String currency = jsonObject.getString("currency");
        String hp = jsonObject.getString("hp");
        String flag = jsonObject.getString("flag");
        //存储返回结果
        String result = tradeService.trade(clientName, ticker, sector, salesperson,
                ric, size, price, currency, hp, flag);
        JSONObject res = new JSONObject();
        res.put("result", result);
        return res;
    }

    @PostMapping({"/showTable"})
    @CrossOrigin
    public JSONObject showTable(@RequestBody JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        String sortType = jsonObject.getString("sortType");
        String gap = jsonObject.getString("gap");
        JSONObject res = this.showTableService.showTable(gap, type, sortType);
        return res;
    }

    @PostMapping("/showGraph")
    @CrossOrigin
    public JSONObject showGraph(@RequestBody JSONObject jsonObject) {
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        JSONObject res = showGraphService.showGraph(endTime, startTime);
        return res;
    }
}
