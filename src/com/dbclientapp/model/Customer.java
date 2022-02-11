package com.dbclientapp.model;

public class Customer {

    private int custId;
    private String custName;
    private String address;
    private String postalCode;
    private String phoneNum;
    private Division division;

    public Customer(int custId, String custName, String address,
                    String postalCode, String phoneNum, Division division) {
        this.custId = custId;
        this.custName = custName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNum = phoneNum;
        this.division = division;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
