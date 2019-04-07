package by.epam.javawebtraining.kudzko.task06.model.logic.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class MyValidatorSAX implements MyValidator {

    private Schema schema = null;
    private String language;
    private SchemaFactory schemaFactory;

    private MyValidatorSAX() {
    }

    public void validate(String filename, String schemname, String
            logname ){
        language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        schemaFactory = SchemaFactory.newInstance(language);
        try {
            //setting checking with XSD
            schema = schemaFactory.newSchema(new File(schemname));
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setSchema(schema);
            //creating object-stax
            SAXParser parser = saxParserFactory.newSAXParser();
            // setting handler errors and executing
            parser.parse(filename,new GreenhousesErrorHandler(logname));
            //log
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static class ValidatorSAXHolder{
        public static final MyValidatorSAX instance = new MyValidatorSAX();
    }

    public static MyValidatorSAX getInstance(){
        return ValidatorSAXHolder.instance;
    }
}
