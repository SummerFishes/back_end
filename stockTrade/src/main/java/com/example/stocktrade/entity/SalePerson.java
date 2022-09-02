package com.example.stocktrade.entity;

public class SalePerson {
    private int salePersonId;
    private String personName;
    private int personAuthority;
    private String personPassword;
    private String personPhone;
    private String flag;

    @Override
    public String toString() {
        return "SalePerson{" +
                "salePersonId=" + salePersonId +
                ", personName='" + personName + '\'' +
                ", personAuthority=" + personAuthority +
                ", personPassword='" + personPassword + '\'' +
                ", personPhone='" + personPhone + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public int getSalePersonId() {
        return salePersonId;
    }

    public void setSalePersonId(int salePersonId) {
        this.salePersonId = salePersonId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAuthority() {
        return personAuthority;
    }

    public void setPersonAuthority(int personAuthority) {
        this.personAuthority = personAuthority;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
