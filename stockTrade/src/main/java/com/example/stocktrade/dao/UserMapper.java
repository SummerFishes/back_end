package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface UserMapper {
    int searchId(String username);
}
