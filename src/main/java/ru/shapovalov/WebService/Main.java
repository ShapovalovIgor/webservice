package ru.shapovalov.WebService;


import ru.shapovalov.WebService.XMLUtils.ServerXML;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class Main {

    public static void main(String[] args) {

        ServerSocket srvSocket = null;
        try {
            try {
                InetAddress ia = InetAddress.getByName(Constant.ADDRESS);
                srvSocket = new ServerSocket(Constant.PORT, Constant.BACKLOG, ia);

                System.out.println("Server started\n\n");

                while(true) {
                    Socket socket = srvSocket.accept();
                    System.err.println("Client accepted");
                    new ServerXML().setSocket(socket);
                }
            } catch(Exception e) {
                System.out.println("Exception : " + e);
            }
        } finally {
            try {
                if (srvSocket != null)
                    srvSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
