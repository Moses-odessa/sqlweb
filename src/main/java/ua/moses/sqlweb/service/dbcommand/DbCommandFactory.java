package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbCommandFactory {
    @Autowired
    private List<DbCommand> dbCommandsList;

    public DbCommand getCommand(String commandTile) {
        for (DbCommand command : dbCommandsList) {
            if (commandTile.equals(command.getTitle())) {
                return command;
            }
        }
        return null;
    }
}
