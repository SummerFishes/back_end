package com.example.stocktrade.entity;

import java.math.BigInteger;

public class OwnerShip {
    private int ownership_id;
    private int user_id;
    private int stock_id;
    private int ownership_num;

    @Override
    public String toString() {
        return "OwnerShip{" +
                "ownership_id=" + ownership_id +
                ", user_id=" + user_id +
                ", stock_id=" + stock_id +
                ", ownership_num=" + ownership_num +
                '}';
    }

    public int getOwnership_id() {
        return ownership_id;
    }

    public void setOwnership_id(int ownership_id) {
        this.ownership_id = ownership_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getOwnership_num() {
        return ownership_num;
    }

    public void setOwnership_num(int ownership_num) {
        this.ownership_num = ownership_num;
    }
}
