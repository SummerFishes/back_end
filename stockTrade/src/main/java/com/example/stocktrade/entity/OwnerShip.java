package com.example.stocktrade.entity;

public class OwnerShip {
    private int ownershipId;
    private int userId;
    private int stockId;
    private int ownershipNum;

    @Override
    public String toString() {
        return "OwnerShip{" +
                "ownershipId=" + ownershipId +
                ", userId=" + userId +
                ", stockId=" + stockId +
                ", ownershipNum=" + ownershipNum +
                '}';
    }

    public int getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(int ownershipId) {
        this.ownershipId = ownershipId;
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

    public int getOwnershipNum() {
        return ownershipNum;
    }

    public void setOwnershipNum(int ownershipNum) {
        this.ownershipNum = ownershipNum;
    }
}
