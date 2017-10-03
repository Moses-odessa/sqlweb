package ua.moses.sqlweb.service.dbcommand;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public abstract class DbCommand {
    private CommandsHref commandsHref;

    DbCommand(CommandsHref commandsHref) {
        this.commandsHref = commandsHref;
    }

    public abstract void run(HttpServletRequest req , Connection connection);

   public String getTitle(){
       return this.commandsHref.getTitle();
   }


    public String getLink() {
        return this.commandsHref.getLink();
    }
}
