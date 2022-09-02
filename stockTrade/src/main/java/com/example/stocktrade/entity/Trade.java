package com.example.stocktrade.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.sql.Date;

public class Trade {
    private int trade_id;
    private int user_id;
    private int stock_id;
    private int sale_person_id;
    private int trade_size;
    private Date trade_date;
    private Timestamp trade_time;
    private int trade_type;
    private int trade_method;
    private float trade_per_price;

    public Trade(int trade_id, int user_id, int stock_id, int sale_person_id, int trade_size, Date trade_date, Timestamp trade_time, int trade_type, int trade_method, float trade_per_price) {
        this.trade_id = trade_id;
        this.user_id = user_id;
        this.stock_id = stock_id;
        this.sale_person_id = sale_person_id;
        this.trade_size = trade_size;
        this.trade_date = trade_date;
        this.trade_time = trade_time;
        this.trade_type = trade_type;
        this.trade_method = trade_method;
        this.trade_per_price = trade_per_price;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "trade_id=" + trade_id +
                ", user_id=" + user_id +
                ", stock_id=" + stock_id +
                ", sale_person_id=" + sale_person_id +
                ", trade_size=" + trade_size +
                ", trade_date=" + trade_date +
                ", trade_time=" + trade_time +
                ", trade_type=" + trade_type +
                ", trade_method=" + trade_method +
                ", trade_per_price=" + trade_per_price +
                '}';
    }

    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
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

    public int getSale_person_id() {
        return sale_person_id;
    }

    public void setSale_person_id(int sale_person_id) {
        this.sale_person_id = sale_person_id;
    }

    public int getTrade_size() {
        return trade_size;
    }

    public void setTrade_size(int trade_size) {
        this.trade_size = trade_size;
    }

    public Date getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = trade_date;
    }

    public Timestamp getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(Timestamp trade_time) {
        this.trade_time = trade_time;
    }

    public int getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(int trade_type) {
        this.trade_type = trade_type;
    }

    public int getTrade_method() {
        return trade_method;
    }

    public void setTrade_method(int trade_method) {
        this.trade_method = trade_method;
    }

    public float getTrade_per_price() {
        return trade_per_price;
    }

    public void setTrade_per_price(float trade_per_price) {
        this.trade_per_price = trade_per_price;
    }
}
