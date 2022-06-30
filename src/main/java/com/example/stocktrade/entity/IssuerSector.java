package com.example.stocktrade.entity;

import java.math.BigInteger;

public class IssuerSector {
    private int issuer_sector_id;
    private String issuer_sector_name;

    @Override
    public String toString() {
        return "IssuerSector{" +
                "issuer_sector_id=" + issuer_sector_id +
                ", issuer_sector_name='" + issuer_sector_name + '\'' +
                '}';
    }

    public int getIssuer_sector_id() {
        return issuer_sector_id;
    }

    public void setIssuer_sector_id(int issuer_sector_id) {
        this.issuer_sector_id = issuer_sector_id;
    }

    public String getIssuer_sector_name() {
        return issuer_sector_name;
    }

    public void setIssuer_sector_name(String issuer_sector_name) {
        this.issuer_sector_name = issuer_sector_name;
    }
}
