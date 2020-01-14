package com.homework.websocket.dao;

import com.homework.websocket.model.Table;

import java.util.Map;
import java.util.Set;

public interface ITableDao {

    void addRow(String tableName, Map<String, Object> row);

    Map<String, Object> getRow(String tableName, Object id);

    Table createTable(String tableName, String idField);

    Table deleteTable(String tableName);

    Set<Map<String, Object>> listRows(String tableName);
}
