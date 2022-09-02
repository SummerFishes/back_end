package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int searchId(String username);
}
