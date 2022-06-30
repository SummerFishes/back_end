package com.example.stocktrade.dao;

import com.example.stocktrade.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface TradeMapper {
    boolean add(Trade trade);

    ArrayList<Map<String, Object>> showTable(Date startTime, Date endTime);
}
