package com.example.stocktrade.service;

import com.alibaba.fastjson.JSONObject;

public interface ShowGraphService {

    public JSONObject showGraph(String startTime, String endTime);
}