package by.epam.javawebtraining.kudzko.task06.controller;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.logic.AbstractBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.BuilderType;
import by.epam.javawebtraining.kudzko.task06.model.logic.MyBuilderFactory;
import by.epam.javawebtraining.kudzko.task06.model.logic.Sorter;
import by.epam.javawebtraining.kudzko.task06.model.logic.comparator.NameComparator;
import by.epam.javawebtraining.kudzko.task06.model.logic.comparator.OriginComparator;
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
import java.util.ArrayList;
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
            LOGGER.warn("XMLReader can not create XMLReader", e);
        } catch (IOException e) {
            LOGGER.warn("XMLReader can not read xml", e);
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
        LOGGER.info("\n===SAX===\n" + flowers1 + "\n");


        //launching DOM builder
        // creating objects on base of Document

        GreenHouseDOMBuilder greenHouseDOMBuilder = GreenHouseDOMBuilder.getInstance();
        greenHouseDOMBuilder.buildListFlowers(fileName);
        List<Flower> flowers2 = greenHouseDOMBuilder.getFlowers();
        LOGGER.info("\n===DOM===\n" + flowers2 + "\n");

        //launching DOM builder
        // creating and writing document
        DOMDocumentCreator.createDocument(greenHouseDOMBuilder.getFlowers());

        // StAX parser

        MyStAXBuilder myStAXBuilder = MyStAXBuilder.getInstance();
        myStAXBuilder.buildListFlowers(fileName);
        List<Flower> flowers3 = myStAXBuilder.getFlowers();
        LOGGER.info("\n===StAX===\n" + flowers3 + "\n");


        // launch parsers with help creator

        MyBuilderFactory factory = new MyBuilderFactory();
        List<List<Flower>> lists = new ArrayList<>();


        for (BuilderType builderType : BuilderType.values() ){
            AbstractBuilder builder = factory.createBuilder(builderType);
            builder.buildListFlowers(fileName);
            List<Flower> flowers = builder.getFlowers();
            LOGGER.info("\n==="+ builderType.name()+"===\n" + flowers +
                    "\n");
            lists.add(flowers);
        }

//        AbstractBuilder saxBuilder = factory.createBuilder(BuilderType.SAX);
//        saxBuilder.buildListFlowers(fileName);
//        List<Flower> flowers4 = saxBuilder.getFlowers();
//        LOGGER.info("\n===SAX===\n" + flowers4 + "\n");
//
//
//        AbstractBuilder staxbuilder = factory.createBuilder(BuilderType.STAX);
//        staxbuilder.buildListFlowers(fileName);
//        List<Flower> flowers5 = staxbuilder.getFlowers();
//        LOGGER.info("\n===StAX===\n" + flowers5 + "\n");
//
//
//        AbstractBuilder domBuilder = factory.createBuilder(BuilderType.DOM);
//        domBuilder.buildListFlowers(fileName);
//        List<Flower> flowers6 = domBuilder.getFlowers();
//        LOGGER.info("\n===DOM===\n" + flowers6 + "\n");


  //     Sorter.sort(lists.get(0), new NameComparator(), new OriginComparator
        // ());

        LOGGER.info("\n===Sorted flowers===\n" + lists.get(0) + "\n");



    }
}
