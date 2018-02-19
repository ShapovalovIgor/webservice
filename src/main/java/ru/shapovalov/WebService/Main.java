package ru.shapovalov.WebService;

import ru.shapovalov.WebService.IOXML.GetXML;
import ru.shapovalov.WebService.Validator.ValidationXML;

public class Main {
    public static void main(String[] args) {
        GetXML getXML = new GetXML();
        getXML.run();

//        ValidationXML validationXML = new ValidationXML("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<request>\n" +
//                "    <request-type>GET-BALANCE</request-type>\n" +
//                "    <extra name=\"login\">123456</extra>\n" +
//                "    <extra name=\"password\">pwd</extra>\n" +
//                "</request>");
//        validationXML.validater();

    }
}
