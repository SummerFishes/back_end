package com.example.stocktrade.entity;

public class IssuerSector {
    private int issuerSectorId;
    private String issuerSectorName;

    @Override
    public String toString() {
        return "IssuerSector{" +
                "issuerSectorId=" + issuerSectorId +
                ", issuerSectorName='" + issuerSectorName + '\'' +
                '}';
    }

    public int getIssuerSectorId() {
        return issuerSectorId;
    }

    public void setIssuerSectorId(int issuerSectorId) {
        this.issuerSectorId = issuerSectorId;
    }

    public String getIssuerSectorName() {
        return issuerSectorName;
    }

    public void setIssuerSectorName(String issuerSectorName) {
        this.issuerSectorName = issuerSectorName;
    }

}
