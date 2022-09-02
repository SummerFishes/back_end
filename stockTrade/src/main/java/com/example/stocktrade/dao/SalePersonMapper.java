package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface SalePersonMapper {
    int searchId(String person_name);
}
