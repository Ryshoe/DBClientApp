package com.dbclientapp.division;

import com.dbclientapp.country.Country;
import com.dbclientapp.util.DataTransferObject;

public class Division implements DataTransferObject {

    private int id;
    private String divisionName;
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
