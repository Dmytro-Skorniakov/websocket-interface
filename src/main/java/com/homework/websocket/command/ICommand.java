package com.homework.websocket.command;

import com.fasterxml.jackson.core.type.TypeReference;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;

import java.util.Map;

public interface ICommand<REQ, RESP> {

    Response<RESP> process(REQ request);

    CommandType getCommandType();

    TypeReference<REQ> getRequestTypeReference();

}
