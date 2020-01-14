package com.homework.websocket.factory;

import com.homework.websocket.command.ICommand;
import com.homework.websocket.model.CommandType;

public interface ICommandFactory {

    <REQ, RESP> ICommand<REQ, RESP> getCommand(CommandType commandType);
}
