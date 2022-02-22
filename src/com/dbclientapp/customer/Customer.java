package com.dbclientapp.customer;

import com.dbclientapp.division.Division;
import com.dbclientapp.util.DataTransferObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer implements DataTransferObject {

    private int id;
    private final StringProperty custName = new SimpleStringProperty();
    private String address;
    private String postalCode;
    private String phoneNum;
    private final ObjectProperty<Division> division = new SimpleObjectProperty<>();

    public Customer(int id, String custName, String address, String postalCode, String phoneNum, Division division) {
        this.id = id;
        this.custName.set(custName);
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNum = phoneNum;
        this.division.set(division);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getCustName() {
        return custName.get();
    }

    public void setCustName(String custName) {
        this.custName.set(custName);
    }

    public StringProperty custNameProperty() {
        return custName;
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
        return division.get();
    }

    public void setDivision(Division division) {
        this.division.set(division);
    }

    public ObjectProperty<Division> divisionProperty() {
        return division;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", division=" + division +
                '}';
    }
}
