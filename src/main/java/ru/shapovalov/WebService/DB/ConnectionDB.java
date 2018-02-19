package ru.shapovalov.WebService.DB;

import ru.shapovalov.WebService.Model.User;
import ru.shapovalov.WebService.Parser.RequestProcessor;

import java.sql.*;

import static ru.shapovalov.WebService.Constant.ADD_USER;
import static ru.shapovalov.WebService.Constant.VALIDATE_USER;

public class ConnectionDB {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "violet";
    private static final String password = "violet";
    private static Connection con;
    private static RequestProcessor requestProcessor;

    public ConnectionDB() {
        requestProcessor = new RequestProcessor();
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx) {
            requestProcessor.responseError();
            System.out.println("Connection DB Exception");
        }
    }


    public void addUser(User user) {
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(ADD_USER);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setDouble(3, user.getSallary());
        } catch (SQLException e) {
            requestProcessor.responseError();
            System.out.println("Add user DB Exception");
        }
    }

    public boolean validateUser(User user) {
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(VALIDATE_USER);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
        } catch (SQLException e) {
            requestProcessor.responseError();
            System.out.println("Validate user DB Exception");
            return false;
        }
        return true;
    }

    public Double getBalance(User user) {
        if(validateUser(user)) {
            try {
                PreparedStatement stmt;
                stmt = con.prepareStatement(VALIDATE_USER);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                return null;
            } catch (SQLException e) {
                requestProcessor.responseError();
                System.out.println("Validate user DB Exception");
            }
        }
        return null;
    }
}
