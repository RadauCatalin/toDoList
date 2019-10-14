package org.fasttrackit.todolist.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfiguration {
    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();

        InputStream inputStream = DataBaseConfiguration.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(inputStream);

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD"));
        } finally {
            //Close inputStream to free up memory
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}