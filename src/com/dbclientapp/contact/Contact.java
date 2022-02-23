package com.dbclientapp.contact;

import com.dbclientapp.util.DataTransferObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact implements DataTransferObject {

    private int id;
    private final StringProperty contactName = new SimpleStringProperty();
    private String email;

    public Contact(int id, String contactName, String email) {
        this.id = id;
        this.contactName.set(contactName);
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName.get();
    }

    public void setContactName(String contactName) {
        this.contactName.set(contactName);
    }

    public StringProperty contactNameProperty() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
