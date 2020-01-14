package com.homework.websocket.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertRowRequest {

    private String tableName;
    private Map<String, Object> rowContent;

}
