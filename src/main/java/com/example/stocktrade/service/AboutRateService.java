package com.example.stocktrade.service;

/**
 * 与汇率获取有关的接口
 * 输入货币，获得该货币与美元的汇率
 * 1单位该货币，可换取多少美元
 */
public interface AboutRateService {

    public float calculate(String from);
}
