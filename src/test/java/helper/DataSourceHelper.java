package helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public abstract class DataSourceHelper {

    protected BasicDataSource dataSource;

    @BeforeEach
    void init() {
        String url = "jdbc:h2:mem:UNIT_TEST;" +
                "MODE=PostgreSQL;IGNORECASE=TRUE;" +
                "INIT=RUNSCRIPT FROM 'classpath:schema.sql'\\;";

        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setMaxTotal(1);
    }

    @AfterEach
    void terminate() throws SQLException {
        dataSource.close();
    }
}
