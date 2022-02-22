package com.dbclientapp.country;

import com.dbclientapp.util.DataTransferObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country implements DataTransferObject {

    private int id;
    private final StringProperty countryName = new SimpleStringProperty();

    public Country(int id, String countryName) {
        this.id = id;
        this.countryName.set(countryName);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName.get();
    }

    public void setCountryName(String countryName) {
        this.countryName.set(countryName);
    }

    public StringProperty countryNameProperty() {
        return countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
