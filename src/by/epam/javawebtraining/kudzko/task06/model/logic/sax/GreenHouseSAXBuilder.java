package by.epam.javawebtraining.kudzko.task06.model.logic.sax;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class GreenHouseSAXBuilder {
    private List<Flower> flowers;
    private GreenHouseHandler greenHouseHandler;
    private XMLReader reader;

    public GreenHouseSAXBuilder() {
        //creating SAX - analyzer
        greenHouseHandler = new GreenHouseHandler();

        try {
            // creating object-handler
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(greenHouseHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void buildListFlowers(String filename){
        try {
            // parsing XML-document
            reader.parse(filename);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        flowers = greenHouseHandler.getFlowers();
    }
}
