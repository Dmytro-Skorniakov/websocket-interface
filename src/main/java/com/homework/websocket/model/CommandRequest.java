package com.homework.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandRequest {

    private CommandType commandType;
    private Map<String, Object> request;

}
