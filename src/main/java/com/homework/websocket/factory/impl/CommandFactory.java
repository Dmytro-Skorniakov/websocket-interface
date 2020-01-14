package com.homework.websocket.factory.impl;

import com.homework.websocket.command.ICommand;
import com.homework.websocket.factory.ICommandFactory;
import com.homework.websocket.model.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommandFactory implements ICommandFactory {

    private Map<CommandType, ICommand> commands;

    @Autowired
    public CommandFactory(List<ICommand> commandList) {
        this.commands = commandList.stream()
                .collect(Collectors.toMap(ICommand::getCommandType, Function.identity()));

    }

    @Override
    public <REQ, RESP> ICommand<REQ, RESP> getCommand(CommandType commandType) {
        return commands.get(commandType);
    }
}
