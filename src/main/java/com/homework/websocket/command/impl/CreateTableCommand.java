package com.homework.websocket.command.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.homework.websocket.command.ICommand;
import com.homework.websocket.dao.ITableDao;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;
import com.homework.websocket.model.Table;
import com.homework.websocket.model.request.CreateTableRequest;
import com.homework.websocket.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTableCommand implements ICommand<CreateTableRequest, Table> {

    private ITableDao tableDao;

    @Autowired
    public CreateTableCommand(ITableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Response<Table> process(CreateTableRequest createTableRequest) {
        Assert.hasText(createTableRequest.getTableName(), "tableName");
        Assert.notNull(createTableRequest.getIdField(), "idField");
        Table table = tableDao.createTable(createTableRequest.getTableName(), createTableRequest.getIdField());
        return new Response<>("Successfully created table with name: " + table.getName(), table);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_TABLE;
    }

    @Override
    public TypeReference<CreateTableRequest> getRequestTypeReference() {
        return new TypeReference<CreateTableRequest>() {
        };
    }
}
