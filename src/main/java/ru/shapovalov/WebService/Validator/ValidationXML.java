package ru.shapovalov.WebService.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class ValidationXML {
    private String string;

    public ValidationXML(String string) {
        this.string = string;
    }

    public boolean validater() {
        SchemaFactory schemaFactory =
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        File schemaLocation =
                new File(new File(".").getAbsolutePath() + "/request.xsd");
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(schemaLocation);
        } catch (SAXException e) {
            System.out.println("Not found schema file!");
            e.printStackTrace();
        }

        Validator validator = schema.newValidator();
        Source source = new StreamSource(new StringReader(string));

        try {
            validator.validate(source);
            System.out.println("Is valid");
        } catch (SAXException e) {
            System.out.println("Not valid");
            return false;
        } catch (IOException e) {
            System.out.println("IO Error");
            return false;
        }
        return true;
    }
}
