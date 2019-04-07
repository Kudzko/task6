package by.epam.javawebtraining.kudzko.task06.controller;

import by.epam.javawebtraining.kudzko.task06.model.logic.sax.GreenHouseSAXBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.SimpleHandler;
import by.epam.javawebtraining.kudzko.task06.model.logic.validator.MyValidatorSAX;
import by.epam.javawebtraining.kudzko.task06.model.logic.validator.MyValidator;
import by.epam.javawebtraining.kudzko.task06.model.logic.validator.MyValidatorSAXXSD;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class Controller {
    public static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static void main(String[] args) {
        String fileName =
                "F:\\git_rep\\task6\\src\\by\\epam\\javawebtraining" +
                        "\\kudzko\\task06\\inputoutputdata\\Task6.xml";
        String schemaName =
                "F:\\git_rep\\task6\\src\\by\\epam\\javawebtraining\\kudzko\\task06\\inputoutputdata\\Task6XSD.xsd";

        MyValidator myValidator = MyValidatorSAXXSD.getInstance();
        myValidator.validate(fileName, schemaName, "log\\log.txt");


        MyValidatorSAX myValidatorSAX = MyValidatorSAX.getInstance();
        myValidatorSAX.validate(fileName, schemaName, "log\\log.txt" );


        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SimpleHandler handler = new SimpleHandler();

            reader.setContentHandler(handler);
            reader.parse(fileName);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("xjc.exe F:\\git_rep\\task6\\src\\by\\epam\\javawebtraining\\kudzko\\task06\\inputoutputdata\\Task6XSD.xsd");
        } catch (IOException e) {
            e.printStackTrace();
        }

        GreenHouseSAXBuilder greenHouseSAXBuilder = new GreenHouseSAXBuilder();
        greenHouseSAXBuilder.buildSetFlowers(fileName);
        System.out.println("=============================================");
        System.out.println(greenHouseSAXBuilder.getFlowers());

    }


}
