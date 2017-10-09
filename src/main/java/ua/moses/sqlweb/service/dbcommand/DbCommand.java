package ua.moses.sqlweb.service.dbcommand;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class DbCommand {
    private CommandsHref commandsHref;
    private static List<DbCommand> dbCommandsList = new ArrayList<>();

    @PostConstruct
    private void init() {
        this.dbCommandsList.add(this);
    }

    DbCommand(CommandsHref commandsHref) {
        this.commandsHref = commandsHref;
    }

    public static DbCommand getCommand(String commandTile) throws Exception {
        for (DbCommand command : dbCommandsList) {
            if (commandTile.equals(command.getLink())) {
                return command;
            }
        }
        throw new Exception("Unknown Command!!!!");
    }

    public abstract void run(HttpServletRequest req, Connection connection);

    public String getTitle() {
        return this.commandsHref.getTitle();
    }


    public String getLink() {
        return this.commandsHref.getLink();
    }
}
