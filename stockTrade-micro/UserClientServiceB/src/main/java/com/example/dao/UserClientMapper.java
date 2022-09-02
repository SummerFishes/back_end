package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserClientMapper {
    public int searchId(String userName);
}
