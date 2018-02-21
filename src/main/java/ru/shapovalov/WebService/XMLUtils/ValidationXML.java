package ru.shapovalov.WebService.XMLUtils;

import org.xml.sax.SAXException;
import ru.shapovalov.WebService.Constant;
import ru.shapovalov.WebService.RequestProcessor;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import static ru.shapovalov.WebService.Constant.XML_SCHEMA;
import static ru.shapovalov.WebService.Constant.XSD_SCHEMA_REQUEST;

public class ValidationXML {
    private String string;

    public ValidationXML(String string) {
        this.string = string;
    }

    public boolean validator() {
        SchemaFactory schemaFactory =
                SchemaFactory.newInstance(Constant.XML_SCHEMA);

        File schemaLocation =
                new File(new File(".").getAbsolutePath() + Constant.XSD_SCHEMA_REQUEST);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(schemaLocation);
        } catch (SAXException e) {
            System.out.println("Not found schema file!");
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
