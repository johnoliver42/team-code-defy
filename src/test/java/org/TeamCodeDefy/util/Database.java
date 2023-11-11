package org.TeamCodeDefy.util;


import com.ibatis.common.jdbc.ScriptRunner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * This class is used to reset the test database for
 * running unit tests.
 *
 * @author pwaite
 * @author John Oliver
 */

public class Database {

    private final Logger logger = LogManager.getLogger(this.getClass());
    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    // private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();

    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            logger.log(Level.ERROR, "Database.loadProperties()...Cannot load the properties file");

        } catch (Exception e) {
            logger.log(Level.ERROR, "Database.loadProperties()..." + e);
        }

    }

    // get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws Exception {
        if (connection != null) return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection" + e);
            }
        }

        connection = null;
    }

    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     * @author John Oliver
     */
    public void runSQL(String sqlFile) {

        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(sqlFile);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            Class.forName(properties.getProperty("driver"));
            connect();

            ScriptRunner scriptRunner = new ScriptRunner(this.connection, false, true);
            scriptRunner.runScript(br);

        } catch (SQLException se) {
            logger.error("SQL Ex: " + se);
        } catch (Exception e) {
            logger.error("Exception: " + e);
        } finally {
            disconnect();
        }

    }
}