package ru.shapovalov.WebService;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.shapovalov.WebService.Model.User;
import sun.rmi.rmic.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URL;

public class ParseXML {

    public User parseXML(){
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
//        is.setCharacterStream(new StringReader(getData.getNewData(
//                new URL(Constants.GOODS_URL),
//                Constants.GOODS_1 + sectionId + Constants.GOODS_2 + page + Constants.GOODS_3)));

        Document doc = db.parse(is);

        NodeList nodes = doc.getElementsByTagName("row");
        NodeList name_goods = nodes.getElementsByTagName("name_goods");
        line = (Element) name_goods.item(0);
        String[] nameGoodsStrArray = getCharacterDataFromElement(line).replace("&amp;", "").replace("quot;", "").split("[(,+,/,|,-]");
        String nameGoodsStr = nameGoodsStrArray[0];

            NodeList name_goods = nodes.getElementsByTagName("name_goods");
            line = (Element) name_goods.item(0);
            String[] nameGoodsStrArray = getCharacterDataFromElement(line).replace("&amp;", "").replace("quot;", "").split("[(,+,/,|,-]");
            String nameGoodsStr = nameGoodsStrArray[0];

            NodeList price = nodes.getElementsByTagName("price");
            line = (Element) price.item(0);
            double priceDoub = Double.valueOf(getCharacterDataFromElement(line).replace(',', '.'));


        }
}
