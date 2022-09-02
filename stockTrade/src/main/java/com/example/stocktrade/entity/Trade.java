package com.example.stocktrade.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Trade {
    private int tradeId;
    private int userId;
    private int stockId;
    private int salePersonId;
    private int tradeSize;
    private Date tradeDate;
    private Timestamp tradeTime;
    private int tradeType;
    private int tradeMethod;
    private float tradePerPrice;

    public Trade(int tradeId, int userId, int stockId, int salePersonId, int tradeSize,
                 Date tradeDate, Timestamp tradeTime, int tradeType, int tradeMethod,
                 float tradePerPrice) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.stockId = stockId;
        this.salePersonId = salePersonId;
        this.tradeSize = tradeSize;
        this.tradeDate = tradeDate;
        this.tradeTime = tradeTime;
        this.tradeType = tradeType;
        this.tradeMethod = tradeMethod;
        this.tradePerPrice = tradePerPrice;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getSalePersonId() {
        return salePersonId;
    }

    public void setSalePersonId(int salePersonId) {
        this.salePersonId = salePersonId;
    }

    public int getTradeSize() {
        return tradeSize;
    }

    public void setTradeSize(int tradeSize) {
        this.tradeSize = tradeSize;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Timestamp getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(int tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public float getTradePerPrice() {
        return tradePerPrice;
    }

    public void setTradePerPrice(float tradePerPrice) {
        this.tradePerPrice = tradePerPrice;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", userId=" + userId +
                ", stockId=" + stockId +
                ", salePersonId=" + salePersonId +
                ", tradeSize=" + tradeSize +
                ", tradeDate=" + tradeDate +
                ", tradeTime=" + tradeTime +
                ", tradeType=" + tradeType +
                ", tradeMethod=" + tradeMethod +
                ", tradePerPrice=" + tradePerPrice +
                '}';
    }
}
