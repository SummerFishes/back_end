package com.example.stocktrade.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IssueSectorMapper {
    String searchId(String issue_sector_name);
}
