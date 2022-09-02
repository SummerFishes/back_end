package com.example.stocktrade.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 展示表格数据的层
 */
public interface ShowTableService {
    JSONObject showTable(String gap, String type, String sortType);
}
