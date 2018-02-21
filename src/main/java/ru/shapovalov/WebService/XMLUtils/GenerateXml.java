package ru.shapovalov.WebService.XMLUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.shapovalov.WebService.Constant;

import javax.print.DocFlavor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static ru.shapovalov.WebService.Constant.*;

public class GenerateXml {

    public GenerateXml() {

    }

    public String generateResponse(int code) {
        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constant.TAG_RESPONSE);
            doc.appendChild(rootElement);

            Element resutCode = doc.createElement(Constant.TAG_RESULT_CODE);
            resutCode.setTextContent(String.valueOf(code));
            rootElement.appendChild(resutCode);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        return docToString(doc);
    }

    public String generateResponseParam(int code, String extra) {
        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constant.TAG_RESPONSE);
            doc.appendChild(rootElement);

            Element resultCode = doc.createElement(Constant.TAG_RESULT_CODE);
            resultCode.setTextContent(String.valueOf(code));
            rootElement.appendChild(resultCode);

            Element staff = doc.createElement(Constant.TAG_EXTRA);
            staff.setTextContent(extra);
            rootElement.appendChild(staff);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        return docToString(doc);
    }

    private String docToString(Document document) {
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            System.out.println("Error convert document to String");
        }
        return writer.toString();
    }
}
