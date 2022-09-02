package com.example.stocktrade.entity;

import java.math.BigInteger;

public class StockType {
    private int type_id;
    private String type;

    @Override
    public String toString() {
        return "StockType{" +
                "type_id=" + type_id +
                ", type='" + type + '\'' +
                '}';
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
