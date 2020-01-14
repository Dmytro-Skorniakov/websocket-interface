package com.homework.websocket.dao.impl;

import com.homework.websocket.dao.ITableDao;
import com.homework.websocket.exception.TableNotFoundException;
import com.homework.websocket.model.Database;
import com.homework.websocket.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class TableDao implements ITableDao {

    private static final Object MODIFICATION_MONITOR = new Object();

    private Database database;

    @Autowired
    public TableDao(Database database) {
        this.database = database;
    }

    @Override
    public Set<Map<String, Object>> listRows(String tableName) {
        Table table = getTable(tableName);
        return new HashSet<>(table.getRows().values());
    }

    @Override
    public Map<String, Object> getRow(String tableName, Object id) {
        Table table = getTable(tableName);
        return table.getRow(id);
    }

    @Override
    public void addRow(String tableName, Map<String, Object> row) {
        synchronized (MODIFICATION_MONITOR) {
            Table table = getTable(tableName);
            Map<Object, Map<String, Object>> rows = table.getRows();
            Object id = row.get(table.getIdField());
            if (id == null) {
                throw new IllegalArgumentException("Wrong format of row. Id field: " + table.getIdField() + " should be present");
            }
            rows.put(id, row);
        }
    }

    @Override
    public Table createTable(String tableName, String idField) {
        synchronized (MODIFICATION_MONITOR) {
            Table table = new Table();
            table.setName(tableName);
            table.setIdField(idField);
            database.getTables().put(tableName, table);
            return table;
        }
    }

    @Override
    public Table deleteTable(String tableName) {
        synchronized (MODIFICATION_MONITOR) {
            return database.getTables().remove(tableName);
        }
    }

    private Table getTable(String tableName) {
        Table table = database.getTables().get(tableName);
        if (table == null) {
            throw new TableNotFoundException("Failed to find table with name :" + tableName);
        }
        return table;
    }

}
