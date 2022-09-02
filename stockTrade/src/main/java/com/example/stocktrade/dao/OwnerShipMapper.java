package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OwnerShipMapper {
    int searchOwnNum(int userId, int stockId);

    int searchSumNum(int stockId);

    int searchId(int userId, int stockId);

    boolean update(int num, int ownershipId);

    boolean add(@Param("userId") int userId, @Param("stockId") int stockId);
}
