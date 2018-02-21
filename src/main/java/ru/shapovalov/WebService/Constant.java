package ru.shapovalov.WebService;

public class Constant {
    //Query
    public static final String ADD_USER = "insert into user (name, password, balance) values(?, ?, ?)";
    public static final String CHECK_NAME = "select 1 from user where name = ?";
    public static final String VALIDATE_USER = "select 1 from user where name = ? and password = ?";
    public static final String GET_USER_BALANCE = "select balance from user where name = ? and password = ?";
    //Type message
    public static final String GET_BALANCE = "GET-BALANCE";
    public static final String CREATE_AGT = "CREATE-AGT";
    //Status
    public static final int OK = 0;
    public static final int USER_FOUND = 1;
    public static final int TECHNICAL_ERROR = 2;
    public static final int NAME_NOT_FOUND = 3;
    public static final int PASSWORD_NOT_VALIDATE = 4;
    //TAG
    public static final String TAG_RESPONSE = "response";
    public static final String TAG_RESULT_CODE = "result-code";
    public static final String TAG_EXTRA = "extra";
    public static final String TAG_REQUEST = "request";
    public static final String TAG_REQUEST_TYPE = "request-type";
    //Connect
    public static final int PORT = 5666;
    public static final String ADDRESS = "localhost";
    public static final int BACKLOG = 99999;

    //Database
    public static final String URL_DB = "jdbc:mysql://localhost:3306/webservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER_DB = "violet";
    public static final String PASSWORD_DB = "violet";
    //XML
    public static final String XSD_SCHEMA_REQUEST = "/request.xsd/";
    public static final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
}
