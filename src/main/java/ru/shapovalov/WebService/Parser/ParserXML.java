package ru.shapovalov.WebService;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.shapovalov.WebService.Model.User;
import ru.shapovalov.WebService.Parser.RequestProcessor;
import sun.rmi.rmic.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URL;

public class ParserXML {

    public User parserXML() {
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
//        is.setCharacterStream(new StringReader(getData.getNewData(
//                new URL(Constants.GOODS_URL),
//                Constants.GOODS_1 + sectionId + Constants.GOODS_2 + page + Constants.GOODS_3)));

        Document doc = db.parse(is);

        NodeList requestTypeNodeList = doc.getElementsByTagName("request-type");
        Element requestTypeNode = (Element) requestTypeNodeList.item(0);
        String requestTypeString = getCharacterDataFromElement(requestTypeNode);

        NodeList loginNodeList = doc.getElementsByTagName("login");
        Element loginNode = (Element) loginNodeList.item(0);
        String loginString = getCharacterDataFromElement(loginNode);

        NodeList passwordNodeList = doc.getElementsByTagName("login");
        Element passwordNode = (Element) passwordNodeList.item(0);
        String passwordString = getCharacterDataFromElement(passwordNode);

        RequestProcessor requestProcessor = new RequestProcessor(requestTypeString, loginString, passwordString);
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
