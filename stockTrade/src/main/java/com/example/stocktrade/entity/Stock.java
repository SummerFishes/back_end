package com.example.stocktrade.entity;

public class Stock {
    private int stockId;
    private int issuerSectorId;
    private int typeId;
    private String ric;
    private String stockName;
    private float stockPrice;
    private int releaseNum;
    private int stockLimit;
    private int flag;

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", issuerSectorId=" + issuerSectorId +
                ", typeId=" + typeId +
                ", ric='" + ric + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stockPrice=" + stockPrice +
                ", releaseNum=" + releaseNum +
                ", stockLimit=" + stockLimit +
                ", flag=" + flag +
                '}';
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getIssuerSectorId() {
        return issuerSectorId;
    }

    public void setIssuerSectorId(int issuerSectorId) {
        this.issuerSectorId = issuerSectorId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getRic() {
        return ric;
    }

    public void setRic(String ric) {
        this.ric = ric;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(int releaseNum) {
        this.releaseNum = releaseNum;
    }

    public int getStockLimit() {
        return stockLimit;
    }

    public void setStockLimit(int stockLimit) {
        this.stockLimit = stockLimit;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
