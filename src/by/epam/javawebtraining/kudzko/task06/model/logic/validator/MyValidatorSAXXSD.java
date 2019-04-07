package by.epam.javawebtraining.kudzko.task06.model.logic.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class MyValidatorSAXXSD implements MyValidator {
    private volatile static MyValidatorSAXXSD uniqueInstance;
    String fileName;
    String schemaName;

    private MyValidatorSAXXSD() {
    }

    public static MyValidatorSAXXSD getInstance(){
        if(uniqueInstance == null){
            synchronized (MyValidatorSAXXSD.class){
                if (uniqueInstance == null){
                    uniqueInstance = new MyValidatorSAXXSD();
                }
            }
        }
        return uniqueInstance;
    }

    public void validate(String fileName, String schemaName, String
            logPath) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);

        try {
            // creating schema
            Schema schema = factory.newSchema(schemaLocation);
            // creating validator
            Validator validator = schema.newValidator();
            // checking document
            Source source = new StreamSource(fileName);

            //handling errors
            GreenhousesErrorHandler greenhousesErrorHandler = new
                    GreenhousesErrorHandler(logPath);
            validator.setErrorHandler(greenhousesErrorHandler);
            validator.validate(source);
           // LOG!!!!!!!!!!
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
