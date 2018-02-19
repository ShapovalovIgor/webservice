package ru.shapovalov.WebService;

public class Constant {
    public static final String ADD_USER = "insert into user (name, password, salary) values(?, ?, ?)";
    public static final String VALIDATE_USER = "select 1 from user where name = ? and password = ?";

    public static final String GET_BALANCE = "GET-BALANCE";
    public static final String CREATE_AGT = "CREATE-AGT";
}
