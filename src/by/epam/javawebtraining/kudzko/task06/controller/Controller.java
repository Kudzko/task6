package by.epam.javawebtraining.kudzko.task06.controller;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.FlowerEnum;
import by.epam.javawebtraining.kudzko.task06.model.logic.dom.DOMDocumentCreator;
import by.epam.javawebtraining.kudzko.task06.model.logic.dom.GreenHouseDOMBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.GreenHouseSAXBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.SimpleHandler;
import by.epam.javawebtraining.kudzko.task06.model.logic.stax.MyStAXBuilder;
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
                ".\\src\\by\\epam\\javawebtraining" +
                        "\\kudzko\\task06\\inputoutputdata\\Task6.xml";
        String schemaName =
                ".\\src\\by\\epam\\javawebtraining\\kudzko\\task06" +
                        "\\inputoutputdata\\Task6XSD.xsd";

        //  validator 1
        MyValidator myValidator = MyValidatorSAXXSD.getInstance();
        myValidator.validate(fileName, schemaName, "log\\log.txt");

        // validator 2
        MyValidatorSAX myValidatorSAX = MyValidatorSAX.getInstance();
        myValidatorSAX.validate(fileName, schemaName, "log\\log.txt" );

        // launching simple handler
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

        // launching command line from java to create class from xml
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("xjc.exe " +
                    ".\\src\\by\\epam\\javawebtraining\\kudzko" +
                    "\\task06\\inputoutputdata\\Task6XSD.xsd");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // launching SAX builder
        GreenHouseSAXBuilder greenHouseSAXBuilder = new GreenHouseSAXBuilder();
        greenHouseSAXBuilder.buildListFlowers(fileName);
        System.out.println("===SAX===");
        System.out.println(greenHouseSAXBuilder.getFlowers());

        //launching DOM builder
        // creating objects on base of Document

        GreenHouseDOMBuilder domBuilder = new GreenHouseDOMBuilder();
        domBuilder.buildSetFlowers(fileName);
        System.out.println("===DOM===");
        System.out.println(domBuilder.getFlowerList());


        //launching DOM builder
        // creating and writing document


        DOMDocumentCreator.createDocument(domBuilder.getFlowerList());

        // StAX parser

        MyStAXBuilder myStAXBuilder = new MyStAXBuilder();
        myStAXBuilder.buildListFlowers(fileName);
        System.out.println("===StAX===");
        System.out.println(myStAXBuilder.getFlowers());


    }


}
