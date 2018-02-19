package ru.shapovalov.WebService.Parser;

import ru.shapovalov.WebService.DB.ConnectionDB;
import ru.shapovalov.WebService.Model.User;

import static ru.shapovalov.WebService.Constant.CREATE_AGT;
import static ru.shapovalov.WebService.Constant.GET_BALANCE;

public class RequestProcessor {
    public RequestProcessor() {
    }

    public RequestProcessor(String typeMessage, String login, String password) {
        User user = new User(login, password);
    switch (typeMessage){
        case CREATE_AGT : createUser(user);
        case GET_BALANCE : responseBalance(user);
        default: responseError();
    }

    }
    public void createUser(User user){

    }

    public void responseBalance(User user){
        ConnectionDB connectionDB = new ConnectionDB();
    Double balance = connectionDB.getBalance(user);
        if(balance != null){

        }else {
            responseError();
        }
    }

    public void responseError(){

    }
}
