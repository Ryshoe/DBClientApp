package com.dbclientapp.division;

import com.dbclientapp.util.DataTransferObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Division implements DataTransferObject {

    private int id;
    private final StringProperty divisionName = new SimpleStringProperty();
    private int countryId;

    public Division(int id, String divisionName, int countryId) {
        this.id = id;
        this.divisionName.set(divisionName);
        this.countryId = countryId;
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
