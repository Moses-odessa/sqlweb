package ua.moses.sqlweb.service.dbcommand;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class CreateTable extends DbCommand {
    CreateTable() {
        super("create_table");
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {

    }
}
