package ua.moses.sqlweb.service.dbcommand;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public abstract class DbCommand {
    private String title;

    DbCommand(String title) {
        this.title = title;
    }

    public abstract void run(HttpServletRequest req , Connection connection);

   public String getTitle(){
       return this.title;
   }
}
