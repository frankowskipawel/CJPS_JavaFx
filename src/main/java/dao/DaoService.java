package dao;

import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoService {

    private Connection connection;

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Config.HOSTNAME + "/" + Config.DATABASENAME + "?useSSL=false", Config.USER, Config.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDatabase() {
        init();
        Statement statement = null;
        try {
            String query;
            int resultSet;
            statement = connection.createStatement();

//            query = "create database people;";
//            resultSet = statement.executeUpdate(query);
            query = "create into people table employees (ID int);";
            resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

}
