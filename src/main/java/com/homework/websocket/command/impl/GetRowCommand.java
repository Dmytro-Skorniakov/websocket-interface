package com.homework.websocket.command.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.homework.websocket.command.ICommand;
import com.homework.websocket.dao.ITableDao;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;
import com.homework.websocket.model.request.GetRowRequest;
import com.homework.websocket.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GetRowCommand implements ICommand<GetRowRequest, Map<String, Object>> {

    private ITableDao tableDao;

    @Autowired
    public GetRowCommand(ITableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Response<Map<String, Object>> process(GetRowRequest getRowRequest) {
        Assert.hasText(getRowRequest.getTableName(), "tableName");
        Assert.notNull(getRowRequest.getId(), "id");
        Map<String, Object> table = tableDao.getRow(getRowRequest.getTableName(),
                getRowRequest.getId());
        return new Response<>("Received row from table: " + getRowRequest.getTableName()
                + " with id : " + getRowRequest.getId(), table);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.GET_ROW;
    }

    @Override
    public TypeReference<GetRowRequest> getRequestTypeReference() {
        return new TypeReference<GetRowRequest>() {
        };
    }
}
