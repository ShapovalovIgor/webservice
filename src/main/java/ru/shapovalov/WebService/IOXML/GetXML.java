package ru.shapovalov.WebService.IOXML;

import ru.shapovalov.WebService.Validator.ValidationXML;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.stream.Collectors;

public class GetXML implements Runnable {
    private ServerSocket serverSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    @Override
    public void run() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            serverSocket = new ServerSocket(5678, 9999, inetAddress);

            String inXML = "";
            String outXML = "5678";

            while (true) {
                Socket connectionSocket = serverSocket.accept();
                objectInputStream = new ObjectInputStream(connectionSocket.getInputStream());
                objectOutputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
                try {
                    inXML = (String) objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(inXML);
                ValidationXML validationXML = new ValidationXML(inXML);
                validationXML.validater();
                objectOutputStream.writeObject(outXML);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
