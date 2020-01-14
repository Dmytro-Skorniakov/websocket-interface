package com.homework.websocket.command.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.homework.websocket.command.ICommand;
import com.homework.websocket.dao.ITableDao;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;
import com.homework.websocket.model.Table;
import com.homework.websocket.model.request.DeleteTableRequest;
import com.homework.websocket.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTableCommand implements ICommand<DeleteTableRequest, Table> {

    private ITableDao tableDao;

    @Autowired
    public DeleteTableCommand(ITableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Response<Table> process(DeleteTableRequest request) {
        Assert.hasText(request.getTableName(), "tableName");
        Table table = tableDao.deleteTable(request.getTableName());
        return new Response<>("Successfully removed table with name: " + table.getName(), table);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_TABLE;
    }

    @Override
    public TypeReference<DeleteTableRequest> getRequestTypeReference() {
        return new TypeReference<DeleteTableRequest>() {
        };
    }
}
