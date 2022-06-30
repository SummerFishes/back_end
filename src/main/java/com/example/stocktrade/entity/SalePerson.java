package com.example.stocktrade.entity;

import java.math.BigInteger;

public class SalePerson {
    private int sale_person_id;
    private String person_name;
    private int person_authority;
    private String person_password;
    private String person_phone;
    private String flag;

    @Override
    public String toString() {
        return "SalePerson{" +
                "sale_person_id=" + sale_person_id +
                ", person_name='" + person_name + '\'' +
                ", person_authority=" + person_authority +
                ", person_password='" + person_password + '\'' +
                ", person_phone='" + person_phone + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public int getSale_person_id() {
        return sale_person_id;
    }

    public void setSale_person_id(int sale_person_id) {
        this.sale_person_id = sale_person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public int getPerson_authority() {
        return person_authority;
    }

    public void setPerson_authority(int person_authority) {
        this.person_authority = person_authority;
    }

    public String getPerson_password() {
        return person_password;
    }

    public void setPerson_password(String person_password) {
        this.person_password = person_password;
    }

    public String getPerson_phone() {
        return person_phone;
    }

    public void setPerson_phone(String person_phone) {
        this.person_phone = person_phone;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
