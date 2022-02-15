package com.dbclientapp.division;

import com.dbclientapp.util.DataTransferObject;

public class Division implements DataTransferObject {

    private int id;
    private String divisionName;
    private int countryId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", divisionName='" + divisionName + '\'' +
                ", country=" + countryId +
                '}';
    }
}
