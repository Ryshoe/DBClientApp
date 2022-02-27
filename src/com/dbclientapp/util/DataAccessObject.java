package com.dbclientapp.util;

import java.sql.Connection;
import java.util.List;

/**
 * Defines CRUD methods for handling SQL queries.
 * @param <T> generic type parameter
 */
public abstract class DataAccessObject <T extends DataTransferObject> {

    protected final Connection connection;

    /**
     * Handles SQL database connection.
     * @param connection
     */
    public DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    /**
     * READ SQL operation using primary key.
     * @param id primary key to search for
     * @return single record as object
     */
    public abstract T findById(int id);

    /**
     * READ SQL operation.
     * @return all records as list
     */
    public abstract List<T> findAll();

    /**
     * UPDATE SQL operation using object.
     * @param dto object to update
     * @return updated object
     */
    public abstract T update(T dto);

    /**
     * CREATE SQL operation using object.
     * @param dto object to create
     * @return created object
     */
    public abstract T create(T dto);

    /**
     * DELETE SQL operation using primary key.
     * @param id primary key to delete
     * @return boolean
     */
    public abstract boolean delete(int id);
}
