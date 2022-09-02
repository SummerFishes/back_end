package com.example.stocktrade.dao;

import com.example.stocktrade.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Mapper
public interface TradeMapper {
    boolean add(Trade trade);

    ArrayList<Map<String, Object>> showTable1(Date startTime, Date endTime, String type);

    ArrayList<Map<String, Object>> showTable2(Date startTime, Date endTime, String type);

    Map<String, Object> showTableTotalBuy(Date startTime, Date endTime);

    Map<String, Object> showTableTotalSell(Date startTime, Date endTime);


}
