package com.dbclientapp.division;

import com.dbclientapp.country.Country;
import com.dbclientapp.util.DataTransferObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Division implements DataTransferObject {

    private int id;
    private final StringProperty divisionName = new SimpleStringProperty();
    private final ObjectProperty<Country> country = new SimpleObjectProperty<>();

    public Division(int id, String divisionName, Country country) {
        this.id = id;
        this.divisionName.set(divisionName);
        this.country.set(country);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName.get();
    }

    public void setDivisionName(String divisionName) {
        this.divisionName.set(divisionName);
    }

    public StringProperty divisionNameProperty() {
        return divisionName;
    }

    public Country getCountry() {
        return country.get();
    }

    public void setCountry(Country country) {
        this.country.set(country);
    }

    public ObjectProperty<Country> countryProperty() {
        return country;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", divisionName='" + divisionName + '\'' +
                ", country=" + country +
                '}';
    }
}
