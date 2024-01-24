
package com.mycompany.retailbanking.app;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author shria
 */
public class DbConnection {
     private static interface Singleton {

        final DbConnection INSTANCE = new DbConnection();
    }

    private final BasicDataSource dataSource;

    private DbConnection() {
        String dbName = "smart_contract";
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/";
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url+dbName);
        ds.setUsername(user);
        ds.setPassword(password);

        this.dataSource = ds;
    }

    public static synchronized Connection getCon() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
    
    
    
}
