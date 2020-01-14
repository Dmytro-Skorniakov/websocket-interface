package com.homework.websocket.command.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.homework.websocket.command.ICommand;
import com.homework.websocket.dao.ITableDao;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;
import com.homework.websocket.model.request.ListRowsRequest;
import com.homework.websocket.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class ListRowsCommand implements ICommand<ListRowsRequest, Set<Map<String, Object>>> {

    private ITableDao tableDao;

    @Autowired
    public ListRowsCommand(ITableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Response<Set<Map<String, Object>>> process(ListRowsRequest request) {
        Assert.hasText(request.getTableName(), "tableName");
        Set<Map<String, Object>> rows = tableDao.listRows(request.getTableName());
        return new Response<>("Successfully listed table rows", rows);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.LIST_ROWS;
    }

    @Override
    public TypeReference<ListRowsRequest> getRequestTypeReference() {
        return new TypeReference<ListRowsRequest>() {
        };
    }
}
