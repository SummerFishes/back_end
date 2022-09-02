package com.example.stocktrade.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Stock {
    private int stock_id;
    private int issuer_sector_id;
    private int type_id;
    private String ric;
    private String stock_name;
    private float stock_price;
    private int release_num;
    private int stock_limit;
    private int flag;

    @Override
    public String toString() {
        return "Stock{" +
                "stock_id=" + stock_id +
                ", issuer_sector_id=" + issuer_sector_id +
                ", type_id=" + type_id +
                ", ric='" + ric + '\'' +
                ", stock_name='" + stock_name + '\'' +
                ", stock_price=" + stock_price +
                ", release_num=" + release_num +
                ", stock_limit=" + stock_limit +
                ", flag=" + flag +
                '}';
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getIssuer_sector_id() {
        return issuer_sector_id;
    }

    public void setIssuer_sector_id(int issuer_sector_id) {
        this.issuer_sector_id = issuer_sector_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getRic() {
        return ric;
    }

    public void setRic(String ric) {
        this.ric = ric;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public float getStock_price() {
        return stock_price;
    }

    public void setStock_price(float stock_price) {
        this.stock_price = stock_price;
    }

    public int getRelease_num() {
        return release_num;
    }

    public void setRelease_num(int release_num) {
        this.release_num = release_num;
    }

    public int getStock_limit() {
        return stock_limit;
    }

    public void setStock_limit(int stock_limit) {
        this.stock_limit = stock_limit;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
