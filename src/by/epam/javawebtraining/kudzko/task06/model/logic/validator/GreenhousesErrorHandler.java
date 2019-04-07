package by.epam.javawebtraining.kudzko.task06.model.logic.validator;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;


public class GreenhousesErrorHandler extends DefaultHandler {

    private Logger logger = Logger.getLogger(String.valueOf(GreenhousesErrorHandler.class));

    public GreenhousesErrorHandler(String log) throws IOException {
        logger.addAppender(new FileAppender(new SimpleLayout(), log));
    }

    public void warning(SAXParseException e ){
        logger.warn(getLineAdress(e) + " - " + e.getMessage());
    }

    public void error(SAXParseException e ){
        logger.warn(getLineAdress(e) + " - " + e.getMessage());
    }

    public void fatal(SAXParseException e ){
        logger.warn(getLineAdress(e) + " - " + e.getMessage());
    }


    private String getLineAdress(SAXParseException e){
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
