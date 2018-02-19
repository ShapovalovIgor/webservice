package ru.shapovalov.WebService.IOXML;

import java.io.*;
import java.net.Socket;

public class TestClient {
    public static void main(String argv[]) throws Exception {
        String sentence = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<request>\n" +
                "    <request-type>GET-BALANCE</request-type>\n" +
                "    <extra name=\"login\">123456</extra>\n" +
                "    <extra name=\"password\">pwd</extra>\n" +
                "</request>";
        String modifiedSentence;
        while (true) {
            Socket clientSocket = new Socket("localhost", 5678);

            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            outToServer.writeObject(sentence);
            outToServer.flush();
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            outToServer.close();

            clientSocket.close();
        }
    }
}
