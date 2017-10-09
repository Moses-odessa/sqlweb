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
    private static List<DbCommand> dbCommands = new ArrayList<>();

    @PostConstruct
    private void init() {
        try {
            DbCommand.getCommand(this.getLink());
        } catch (Exception e) {
            dbCommands.add(this);
        }
    }

    DbCommand(CommandsHref commandsHref) {
        this.commandsHref = commandsHref;
    }

    public static DbCommand getCommand(String commandLink) throws Exception {
        for (DbCommand command : dbCommands) {
            if (commandLink.equals(command.getLink())) {
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
