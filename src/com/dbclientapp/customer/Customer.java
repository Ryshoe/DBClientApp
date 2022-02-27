package com.dbclientapp.customer;

import com.dbclientapp.division.Division;
import com.dbclientapp.util.DataTransferObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Handles customers as an object.
 */
public class Customer implements DataTransferObject {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private String custName;
    private String address;
    private String postalCode;
    private String phoneNum;
    private final ObjectProperty<Division> division = new SimpleObjectProperty<>();

    @Override
    public int getId() {
        return id.get();
    }

    @Override
    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
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
