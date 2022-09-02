package com.example.stocktrade.entity;

public class StockType {
    private int typeId;
    private String type;

    @Override
    public String toString() {
        return "StockType{" +
                "typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
