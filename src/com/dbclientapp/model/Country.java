package com.dbclientapp.model;

import com.dbclientapp.util.DataTransferObject;

public class Country implements DataTransferObject {

    private int id;
    private String countryName;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
