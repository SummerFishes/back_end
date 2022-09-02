package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface StockMapper {
    int searchId(String stockName, String stockType, String ric);

    int searchIssueAndStock(int stockId, int sectorId);

    Map<String, Integer> searchLimitAndRelease(int stockId);
}
