package ru.shapovalov.WebService;

import ru.shapovalov.WebService.DB.DBUtils;
import ru.shapovalov.WebService.Model.User;
import ru.shapovalov.WebService.XMLUtils.GenerateXml;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class RequestProcessor {
    private Socket socket;

    public RequestProcessor(Socket socket) {
        this.socket = socket;
    }

    public RequestProcessor(String typeMessage, String login, String password, Socket socket) {
        System.out.println("Type message:" + typeMessage);

        this.socket = socket;
        User user = new User(login, password);
        switch (typeMessage) {
            case Constant.CREATE_AGT:
                createUser(user);
                break;
            case Constant.GET_BALANCE:
                responseBalance(user);
                break;
            default:
                System.out.println("Type message not found:" + typeMessage);
                responseTechnicalError();
        }
    }

    public void createUser(User user) {
        DBUtils DBUtils = new DBUtils(socket);
        boolean createUser = DBUtils.addUser(user);
        if (createUser == true) {
            send(Constant.OK);
        }
    }

    public void responseBalance(User user) {
        DBUtils DBUtils = new DBUtils(socket);
        Double balance = DBUtils.getBalance(user);
        System.out.println("bbb" + balance);

        if (balance != null) {
            send(Constant.OK, String.valueOf(balance));
        }
    }

    public void responseUserNotFound() {
        send(Constant.NAME_NOT_FOUND);
    }

    public void responseUserFound() {
        send(Constant.USER_FOUND);
    }

    public void responsePasswordNotValidate() {
        send(Constant.PASSWORD_NOT_VALIDATE);
    }

    public void responseTechnicalError() {
        send(Constant.TECHNICAL_ERROR);
    }

    private void send(int status) {
        send(status, null);
    }

    private void send(int status, String extra) {
        GenerateXml generateXml = new GenerateXml();
        String response;
        System.out.println("send start");
        if (null == extra) {
            response = generateXml.generateResponse(status);
        } else {
            response = generateXml.generateResponseParam(status, extra);
        }
        System.out.println("send" + response);
        try {
            OutputStream sout = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(sout);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("POST /webservice HTTP/1.0rn\n");
            stringBuilder.append("Content-Length: "+response.length()+"rn\n");
            stringBuilder.append("Content-Type: application/xml\n");
            stringBuilder.append("rn\n");
            stringBuilder.append(response);
            dos.writeUTF(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
