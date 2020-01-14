package com.homework.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    private String name, idField;
    private Map<Object, Map<String, Object>> rows = new HashMap<>();

    public Map<String, Object> getRow(Object id) {
        return this.rows.get(id);
    }

}
