package ru.shapovalov.WebService.XMLUtils;

import ru.shapovalov.WebService.RequestProcessor;

import java.io.*;
import java.net.*;


public class ServerXML extends Thread {

    private  Socket socket;

    public void setSocket(Socket socket)
    {
        this.socket = socket;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    @Override
    public void run() {
        try {
            InputStream  sin  = socket.getInputStream();
            DataInputStream  dis = new DataInputStream (sin);
            String inXML = null;
            while(true) {
                inXML = dis.readUTF();
                inXML = inXML.split("\nrn\n")[1];
                System.out.println(inXML);
                ValidationXML validationXML = new ValidationXML(inXML);
                boolean valid = validationXML.validator();
                RequestProcessor requestProcessor = new RequestProcessor(socket);
                if(valid){
                    ParserXML parserXML = new ParserXML(inXML, socket);
                }else {
                    requestProcessor.responseTechnicalError();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
