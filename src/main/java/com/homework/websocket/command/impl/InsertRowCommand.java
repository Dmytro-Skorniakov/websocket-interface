package com.homework.websocket.command.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.homework.websocket.command.ICommand;
import com.homework.websocket.dao.ITableDao;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;
import com.homework.websocket.model.request.InsertRowRequest;
import com.homework.websocket.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InsertRowCommand implements ICommand<InsertRowRequest, Map<String, Object>> {

    private ITableDao tableDao;

    @Autowired
    public InsertRowCommand(ITableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Response<Map<String, Object>> process(InsertRowRequest request) {
        Assert.hasText(request.getTableName(), "tableName");
        Assert.notNull(request.getRowContent(), "rowContent");
        tableDao.addRow(request.getTableName(), request.getRowContent());
        return new Response<>("Successfully added row", request.getRowContent());
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.INSERT_ROW;
    }

    @Override
    public TypeReference<InsertRowRequest> getRequestTypeReference() {
        return new TypeReference<InsertRowRequest>() {
        };
    }
}
