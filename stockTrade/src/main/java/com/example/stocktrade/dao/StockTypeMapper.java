package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockTypeMapper {
    boolean updateRate(@Param("currency") String currency, @Param("rate") float rate);

    float getRate(String currency);
}
