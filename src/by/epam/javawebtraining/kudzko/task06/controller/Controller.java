package by.epam.javawebtraining.kudzko.task06.controller;

import by.epam.javawebtraining.kudzko.task06.model.logic.AbstractBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.MyBuilderFactory;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Controller {
    public static final Logger LOGGER;
    public static final String CONFIG_PATH = ".\\src\\by\\epam\\javawebtraining" +
            "\\kudzko\\task06\\controller\\StartConfiguration.properties";

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            FileInputStream config = new FileInputStream(CONFIG_PATH);
            properties.load(config);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = properties.getProperty("fileName");
        String schemaName = properties.getProperty("schemaName");
        String logPathForValidator = properties.getProperty("logPathForValidator");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+fileName);

        //  validator 1
        MyValidator myValidator = MyValidatorSAXXSD.getInstance();
        myValidator.validate(fileName, schemaName, logPathForValidator);

        // validator 2
        MyValidatorSAX myValidatorSAX = MyValidatorSAX.getInstance();
        myValidatorSAX.validate(fileName, schemaName, logPathForValidator);

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
        String createClassFromXMLDoc = properties.getProperty
                ("createClassFromXMLDoc");
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("xjc.exe " + createClassFromXMLDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // launching SAX builder
        GreenHouseSAXBuilder greenHouseSAXBuilder = GreenHouseSAXBuilder.getInstance();
        greenHouseSAXBuilder.buildListFlowers(fileName);
        System.out.println("===SAX===");
        System.out.println(greenHouseSAXBuilder.getFlowers());

        //launching DOM builder
        // creating objects on base of Document

        GreenHouseDOMBuilder greenHouseDOMBuilder = GreenHouseDOMBuilder.getInstance();
        greenHouseDOMBuilder.buildListFlowers(fileName);
        System.out.println("===DOM===");
        System.out.println(greenHouseDOMBuilder.getFlowers());


        //launching DOM builder
        // creating and writing document


        DOMDocumentCreator.createDocument(greenHouseDOMBuilder.getFlowers());

        // StAX parser

        MyStAXBuilder myStAXBuilder = MyStAXBuilder.getInstance();
        myStAXBuilder.buildListFlowers(fileName);
        System.out.println("===StAX===");
        System.out.println(myStAXBuilder.getFlowers());


        // launch parsers with help creator

        MyBuilderFactory factory = new MyBuilderFactory();

        System.out.println("===SAX===");
        AbstractBuilder saxBuilder = factory.createBuilder(MyBuilderFactory
                .ParserType.SAX);
        saxBuilder.buildListFlowers(fileName);
        System.out.println(saxBuilder.getFlowers());

        System.out.println("===StAX===");
        AbstractBuilder staxbuilder = factory.createBuilder(MyBuilderFactory
                .ParserType.STAX);
        staxbuilder.buildListFlowers(fileName);
        System.out.println(staxbuilder.getFlowers());

        System.out.println("===DOM===");
        AbstractBuilder domBuilder = factory.createBuilder(MyBuilderFactory
                .ParserType.DOM);
        domBuilder.buildListFlowers(fileName);
        System.out.println(domBuilder.getFlowers());


    }


}
