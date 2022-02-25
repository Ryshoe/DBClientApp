package com.dbclientapp.util;

import java.sql.Connection;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {

    protected final Connection connection;

    // Handles database connection
    public DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    // Defines CRUD methods
    public abstract T findById(int id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract boolean delete(int id);
}
