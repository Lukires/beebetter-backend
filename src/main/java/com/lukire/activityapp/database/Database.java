package com.lukire.activityapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Database {

    private final String user = "beebetter";
    private final String dbName = "beebetter";
    private final String pass = "bee";
    private final String ip = "127.0.0.1";
    private final String port = "3306";
    private static final Database database = new Database();
    private Connection connection;

    private Database() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mariadb://"+ip+":"+port+"/"+dbName,user,pass);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getDatabase() {
        return database;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet select(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(String query) {
        try {
            connection.createStatement().executeQuery(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}
