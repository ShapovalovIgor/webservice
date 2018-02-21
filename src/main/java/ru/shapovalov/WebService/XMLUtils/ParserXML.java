package ru.shapovalov.WebService.XMLUtils;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.shapovalov.WebService.Constant;
import ru.shapovalov.WebService.RequestProcessor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;

import static ru.shapovalov.WebService.Constant.TAG_EXTRA;
import static ru.shapovalov.WebService.Constant.TAG_REQUEST_TYPE;

public class ParserXML {

    public ParserXML(String inXml, Socket socket) throws IOException {
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(inXml));

        Document doc = null;
        try {
            doc = db.parse(is);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        NodeList requestTypeNodeList = doc.getElementsByTagName(Constant.TAG_REQUEST_TYPE);
        Element requestTypeNode = (Element) requestTypeNodeList.item(0);
        String requestTypeString = getCharacterDataFromElement(requestTypeNode);

        NodeList nameNodeList = doc.getElementsByTagName(Constant.TAG_EXTRA);
        Element nameNode = (Element) nameNodeList.item(0);
        String nameString = getCharacterDataFromElement(nameNode);

        NodeList passwordNodeList = doc.getElementsByTagName(Constant.TAG_EXTRA);
        Element passwordNode = (Element) passwordNodeList.item(1);
        String passwordString = getCharacterDataFromElement(passwordNode);
        System.out.println("Parser " + Constant.TAG_REQUEST_TYPE + requestTypeString + Constant.TAG_EXTRA
                + nameString + Constant.TAG_EXTRA + passwordString);
        RequestProcessor requestProcessor = new RequestProcessor(requestTypeString, nameString, passwordString, socket);
    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
