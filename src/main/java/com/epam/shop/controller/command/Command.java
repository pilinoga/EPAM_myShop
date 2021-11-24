package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.dao.Role;

public interface Command {

    /**
     * Method is executed by controller to certain command
     *
     * @param context request from user
     * @return result of command by ResponseContext
     */
    ResponseContext execute(RequestContext context);

    /**
     * Method to extract command implementation from CommandType
     *
     * @param name parameter from request
     * @return Command representation for name parameter
     */
    static Command of(String name){
        return CommandType.getCommand(name);
    }

    /**
     * Method to check equality of user role and command access level
     *
     * @param role user role
     * @param commandName command name for checking level access
     * @return result of checking
     */
    static boolean isEqualAccess(Role role, String commandName){
        return CommandType.isEqualAccess(role, commandName);
    }
}
