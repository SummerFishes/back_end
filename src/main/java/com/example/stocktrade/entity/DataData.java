package com.example.stocktrade.entity;

import java.math.BigInteger;
import java.sql.Date;

public class DataData {
    private int date_date_id;
    private Date data_date;
    private int buy_num;
    private int sell_num;
    private int flag;

    @Override
    public String toString() {
        return "DataData{" +
                "date_date_id=" + date_date_id +
                ", data_date=" + data_date +
                ", buy_num=" + buy_num +
                ", sell_num=" + sell_num +
                ", flag=" + flag +
                '}';
    }

    public int getDate_date_id() {
        return date_date_id;
    }

    public void setDate_date_id(int date_date_id) {
        this.date_date_id = date_date_id;
    }

    public Date getData_date() {
        return data_date;
    }

    public void setData_date(Date data_date) {
        this.data_date = data_date;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }

    public int getSell_num() {
        return sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
