package ru.shapovalov.WebService.DB;

import ru.shapovalov.WebService.Constant;
import ru.shapovalov.WebService.Model.User;
import ru.shapovalov.WebService.RequestProcessor;

import java.net.Socket;
import java.sql.*;

public class DBUtils {

    private static Connection con;
    private static RequestProcessor requestProcessor;

    public DBUtils(Socket socket) {
        requestProcessor = new RequestProcessor(socket);
        try {
            con = DriverManager.getConnection(Constant.URL_DB, Constant.USER_DB, Constant.PASSWORD_DB);
        } catch (SQLException sqlEx) {
            requestProcessor.responseTechnicalError();
            System.out.println("Connection DB Exception");
        }
    }

    public boolean addUser(User user) {
        if (!checkName(user)) {
            try {
                System.out.println("Add user: " + user.getBalance());
                PreparedStatement stmt;
                stmt = con.prepareStatement(Constant.ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setDouble(3, user.getBalance());
                int i = stmt.executeUpdate();
                if (i == 1) {
                    return true;
                }
            } catch (SQLException e) {
                requestProcessor.responseTechnicalError();
                System.out.println("Add user DB Exception");
                e.printStackTrace();
            }
        } else {
            requestProcessor.responseUserFound();
            System.out.println("User found Exception");
            return false;
        }
        return false;
    }

    public Double getBalance(User user) {
        if (checkName(user)) {
            if (checkPassword(user)) {
                try {
                    PreparedStatement stmt;
                    stmt = con.prepareStatement(Constant.GET_USER_BALANCE);
                    stmt.setString(1, user.getName());
                    stmt.setString(2, user.getPassword());
                    System.out.println("query:" + Constant.GET_USER_BALANCE + "name:" + user.getName() + "password" + user.getPassword());
                    ResultSet resultSet = stmt.executeQuery();

                    if (resultSet.next()) {
                        Double balance = resultSet.getDouble(1);
                        if (balance != null) {
                            System.out.println("bbb");
                            return balance;
                        }
                    }
                } catch (SQLException e) {
                    requestProcessor.responseTechnicalError();
                    e.printStackTrace();
                    System.out.println("Validate user DB Exception");
                }
            } else {
                requestProcessor.responsePasswordNotValidate();
                System.out.println("Password not validate");
            }
        } else {
            requestProcessor.responseUserNotFound();
            System.out.println("Name not found");
        }
        return null;
    }

    public boolean checkName(User user) {
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(Constant.CHECK_NAME);
            stmt.setString(1, user.getName());
            ResultSet resultSet = stmt.executeQuery();
            if (getRowCount(resultSet) == 1) {
                return true;
            }
        } catch (SQLException e) {
            requestProcessor.responseTechnicalError();
            e.printStackTrace();
            System.out.println("Validate name DB Exception");
            return false;
        }
        return false;
    }

    public boolean checkPassword(User user) {
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(Constant.VALIDATE_USER);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            ResultSet resultSet = stmt.executeQuery();
            if (getRowCount(resultSet) == 1) {
                return true;
            }
        } catch (SQLException e) {
            requestProcessor.responseTechnicalError();
            System.out.println("Validate password DB Exception");
            return false;
        }
        return false;
    }

    private int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }
        return 0;
    }
}
