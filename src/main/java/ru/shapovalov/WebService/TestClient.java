package ru.shapovalov.WebService;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestClient implements Runnable {
    private static final String CREATE_USER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<request>\n" +
            "    <request-type>CREATE-AGT</request-type>\n" +
            "    <extra name=\"login\">123456</extra>\n" +
            "    <extra name=\"password\">pwd</extra>\n" +
            "</request>";

    private static final String GET_BALANCE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<request>\n" +
            "    <request-type>GET-BALANCE</request-type>\n" +
            "    <extra name=\"login\">123456</extra>\n" +
            "    <extra name=\"password\">pwd</extra>\n" +
            "</request>";
    private String sendXML;

    public TestClient(String sendXML) {
        this.sendXML = sendXML;
    }

    public static void main(String argv[]) throws Exception {
        TestClient oneTest = new TestClient(CREATE_USER);
        TestClient twoTest = new TestClient(GET_BALANCE);
        TestClient threeTest = new TestClient(CREATE_USER);
        TestClient fourTest = new TestClient(GET_BALANCE);

        ExecutorService executorService = Executors.newFixedThreadPool(4);//Если измень на 1 то потоки будут выполнятся последовательно
        executorService.submit(oneTest);
        executorService.submit(twoTest);
        executorService.submit(threeTest);
        executorService.submit(fourTest);
        TimeUnit.SECONDS.sleep(3);
    }

    @Override
    public void run() {
        String modifiedSentence;
        System.out.println("Welcome to Client side");

        while (true) {
            try {
                Socket clientSocket = new Socket("localhost", 5666);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("POST /webservice HTTP/1.0rn\n");
                stringBuilder.append("Content-Length: " + sendXML.length() + "rn\n");
                stringBuilder.append("Content-Type: application/xml\n");
                stringBuilder.append("rn\n");
                stringBuilder.append(sendXML);
                outToServer.writeUTF(stringBuilder.toString());
                outToServer.flush();
                modifiedSentence = inFromServer.readUTF();
                modifiedSentence = modifiedSentence.split("\nrn\n")[1];

                System.out.println("FROM SERVER: " + modifiedSentence);
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
