package com.example.stocktrade.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 与图有关的接口
 */
public interface ShowGraphService {

    public JSONObject showGraph(String startTime, String endTime);
}