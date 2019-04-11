package by.epam.javawebtraining.kudzko.task06.controller;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
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
import java.util.List;
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
            LOGGER.warn("Not found property file with config", e);
        } catch (IOException e) {
            LOGGER.warn("couldn't read property in file config", e);
        }
        String fileName = properties.getProperty("fileName");
        String schemaName = properties.getProperty("schemaName");
        String logPathForValidator = properties.getProperty("logPathForValidator");

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
            LOGGER.warn("Command to create class Flower from xml can not read", e);
        }

        // launching SAX builder
        GreenHouseSAXBuilder greenHouseSAXBuilder = GreenHouseSAXBuilder.getInstance();
        greenHouseSAXBuilder.buildListFlowers(fileName);
        List<Flower> flowers1 = greenHouseSAXBuilder.getFlowers();
        LOGGER.info("===SAX===\n" + flowers1);


        //launching DOM builder
        // creating objects on base of Document

        GreenHouseDOMBuilder greenHouseDOMBuilder = GreenHouseDOMBuilder.getInstance();
        greenHouseDOMBuilder.buildListFlowers(fileName);
        List<Flower> flowers2 = greenHouseDOMBuilder.getFlowers();
        LOGGER.info("===DOM===\n" + flowers2);

        //launching DOM builder
        // creating and writing document
        DOMDocumentCreator.createDocument(greenHouseDOMBuilder.getFlowers());

        // StAX parser

        MyStAXBuilder myStAXBuilder = MyStAXBuilder.getInstance();
        myStAXBuilder.buildListFlowers(fileName);
        List<Flower> flowers3 = myStAXBuilder.getFlowers();
        LOGGER.info("===StAX===\n" + flowers3);


        // launch parsers with help creator

        MyBuilderFactory factory = new MyBuilderFactory();

        AbstractBuilder saxBuilder = factory.createBuilder(MyBuilderFactory
                .ParserType.SAX);
        saxBuilder.buildListFlowers(fileName);
        List<Flower> flowers4 = saxBuilder.getFlowers();
        LOGGER.info("===SAX===\n" + flowers3);

        System.out.println("===StAX===");
        AbstractBuilder staxbuilder = factory.createBuilder(MyBuilderFactory
                .ParserType.STAX);
        staxbuilder.buildListFlowers(fileName);
        List<Flower> flowers5 = staxbuilder.getFlowers();
        LOGGER.info("===StAX===\n" + flowers5);

        System.out.println("===DOM===");
        AbstractBuilder domBuilder = factory.createBuilder(MyBuilderFactory
                .ParserType.DOM);
        domBuilder.buildListFlowers(fileName);
        List<Flower> flowers6 = domBuilder.getFlowers();
        LOGGER.info("===DOM===\n" + flowers6);
    }
}
