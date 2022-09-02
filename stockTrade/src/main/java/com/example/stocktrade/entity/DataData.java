package com.example.stocktrade.entity;

import java.sql.Date;

public class DataData {
    private int dateDataId;
    private Date dataDate;
    private int buyNum;
    private int sellNum;
    private int flag;

    @Override
    public String toString() {
        return "DataData{" +
                "dateDataId=" + dateDataId +
                ", dataDate=" + dataDate +
                ", buyNum=" + buyNum +
                ", sellNum=" + sellNum +
                ", flag=" + flag +
                '}';
    }

    public int getDateDataId() {
        return dateDataId;
    }

    public void setDateDataId(int dateDataId) {
        this.dateDataId = dateDataId;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
