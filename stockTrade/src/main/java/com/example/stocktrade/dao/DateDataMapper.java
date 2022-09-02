package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Mapper
public interface DateDataMapper {
    int searchId();

    boolean updateBuy(float buyNum, int dateDataId);

    boolean updateSell(float sellNum, int dateDataId);

    boolean add();

    ArrayList<Map<String, Object>> showGraph(Date startTime, Date endTime);
}
