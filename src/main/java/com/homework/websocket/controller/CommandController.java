package com.homework.websocket.controller;

import com.homework.websocket.command.ICommand;
import com.homework.websocket.factory.ICommandFactory;
import com.homework.websocket.model.CommandRequest;
import com.homework.websocket.model.CommandType;
import com.homework.websocket.model.Response;
import com.homework.websocket.utils.Assert;
import com.homework.websocket.utils.ExceptionHandler;
import com.homework.websocket.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class CommandController {

    private ICommandFactory commandFactory;

    @Autowired
    public CommandController(ICommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @MessageMapping("/database")
    @SendTo("/topic/response")
    public Response<?> handle(CommandRequest commandRequest) {
        try {
            Map<String, Object> requestMap = commandRequest.getRequest();
            Assert.notNull(requestMap, "request");
            CommandType commandType = commandRequest.getCommandType();
            Assert.notNull(commandType, "commandType");
            ICommand command = commandFactory.getCommand(commandType);
            Object request = JsonUtils.parseJsonToObject(requestMap, command.getRequestTypeReference());
            return command.process(request);
        } catch (Exception ex) {
            return ExceptionHandler.handleException(ex);
        }
    }

}
