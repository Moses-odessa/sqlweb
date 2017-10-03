package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbCommandFactory {

    private List<DbCommand> dbCommandsList;

    @Autowired
    public void setDbCommandsList(List<DbCommand> dbCommandsList) {
        this.dbCommandsList = dbCommandsList;
    }


    public DbCommand getCommand(String commandTile) throws Exception {
        for (DbCommand command : dbCommandsList) {
            if (commandTile.equals(command.getLink())) {
                return command;
            }
        }
        throw new Exception("Unknown Command!!!!");
    }
}
