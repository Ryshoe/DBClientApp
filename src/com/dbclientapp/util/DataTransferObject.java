package com.dbclientapp.util;

/**
 * Interface to define primary key (id) methods for object model classes.
 */
public interface DataTransferObject {

    /**
     * Gets id of object
     * @return id as int
     */
    int getId();

    /**
     * Sets id of object.
     * @param id int to set as id
     */
    void setId(int id);
}
