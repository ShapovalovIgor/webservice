package ru.shapovalov.WebService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GetXML implements Runnable {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    @Override
    public void run() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            serverSocket = new ServerSocket(5678, 10, inetAddress);
            socket = serverSocket.accept();
            while (true) {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println(objectInputStream.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
