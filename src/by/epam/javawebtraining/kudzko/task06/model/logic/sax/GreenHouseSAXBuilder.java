package by.epam.javawebtraining.kudzko.task06.model.logic.sax;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.logic.AbstractBuilder;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class GreenHouseSAXBuilder extends AbstractBuilder {
    public static final Logger LOGGER;

    private volatile static GreenHouseSAXBuilder instance;
    private GreenHouseHandler greenHouseHandler;
    private XMLReader reader;

    static {
        LOGGER = Logger.getRootLogger();
    }

    {
        greenHouseHandler = GreenHouseHandler.getInstance();

        try {
            // creating object-handler
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(greenHouseHandler);
        } catch (SAXException e) {
            LOGGER.warn("Can not create xml reader", e);
        }
    }

    private GreenHouseSAXBuilder() {

    }

    private GreenHouseSAXBuilder(List<Flower> flowers) {
        super(flowers);
    }

    public static GreenHouseSAXBuilder getInstance() {
        if (instance == null) {
            synchronized (AbstractBuilder.class) {
                if (instance == null) {
                    instance = new GreenHouseSAXBuilder();
                }
            }
        }
        return instance;
    }

    public static GreenHouseSAXBuilder getInstance(List<Flower> flowers) {
        if (instance == null) {
            synchronized (AbstractBuilder.class) {
                if (instance == null) {
                    instance = new GreenHouseSAXBuilder(flowers);
                }
            }
        }
        return instance;
    }

    @Override
    public void buildListFlowers(String filename) {
        try {
            // parsing XML-document
            reader.parse(filename);
        } catch (SAXException | IOException e) {
            LOGGER.warn("Can not parse xml", e);
        }
        flowers = greenHouseHandler.getFlowers();
    }
}
