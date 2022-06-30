package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

@Mapper
public interface DateDataMapper {
    int searchId();

    boolean updateBuy(float buy_num, int date_date_id);

    boolean updateSell(float sell_num, int date_date_id);

    boolean add();
}
