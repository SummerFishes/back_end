package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.Map;

@Mapper
public interface StockMapper {
    int searchId(String stock_name);

    Map<String, Integer> searchLimitAndRelease(int stock_id);
}
