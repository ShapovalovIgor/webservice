package ru.shapovalov.WebService.DB;

import ru.shapovalov.WebService.Model.User;

import java.sql.*;

import static ru.shapovalov.WebService.Constant.ADD_USER;

public class ConnectionDB {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "violet";
    private static final String password = "violet";
    private static Connection con;

    public ConnectionDB() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx) {
            System.out.println("Connection DB Exception");
        }
    }


    public boolean addUser(User user) {
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(ADD_USER);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setDouble(3, user.getSallary());
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
