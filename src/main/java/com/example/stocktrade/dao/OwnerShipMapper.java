package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

@Mapper
public interface OwnerShipMapper {
    int searchOwnNum(int user_id, int stock_id);

    int searchSumNum(int stock_id);

    int searchId(int user_id, int stock_id);

    boolean update(int num, int ownership_id);

    boolean add(@Param("user_id") int user_id, @Param("stock_id") int stock_id);
}
