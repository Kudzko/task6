package by.epam.javawebtraining.kudzko.task06.model.logic.dom;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.logic.AbstractBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GreenHouseDOMBuilder extends AbstractBuilder {

    public static final String ELEMENT_FLOWER = Constants.ELEMENT_FLOWER;
    public static final String ELEMENT_NAME = Constants.ELEMENT_NAME;
    public static final String ELEMENT_SOIL = Constants.ELEMENT_SOIL;
    public static final String ELEMENT_ORIGIN = Constants.ELEMENT_ORIGIN;
    public static final String ELEMENT_SIZE = Constants.ELEMENT_SIZE;
    public static final String ELEMENT_COLOR = Constants.ELEMENT_COLOR;
    public static final String ELEMENT_LEAF_COLOR = Constants.ELEMENT_LEAF_COLOR;
    public static final String ELEMENT_TEMPERATURE = Constants.ELEMENT_TEMPERATURE;
    public static final String ELEMENT_LIGHTING = Constants.ELEMENT_LIGHTING;
    public static final String ELEMENT_WATERING = Constants.ELEMENT_WATERING;
    public static final String ELEMENT_MULTIPLYING = Constants.ELEMENT_MULTIPLYING;

    private volatile static GreenHouseDOMBuilder instance;
    private DocumentBuilder documentBuilder;

    private GreenHouseDOMBuilder() {
        this.flowers = new ArrayList<>();
        // creating DOM analyzer
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public GreenHouseDOMBuilder(List<Flower> flowers) {
        super(flowers);
        // creating DOM analyzer
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static GreenHouseDOMBuilder getInstance(){
        if (instance == null){
            synchronized (GreenHouseDOMBuilder.class){
                if (instance == null){
                    instance = new GreenHouseDOMBuilder();
                }
            }
        }
        return instance;
    }

    public static GreenHouseDOMBuilder getInstance(List<Flower> flowers){
        if (instance == null){
            synchronized (GreenHouseDOMBuilder.class){
                if (instance == null){
                    instance = new GreenHouseDOMBuilder(flowers);
                }
            }
        }
        return instance;
    }

    @Override
    public void buildListFlowers(String filename) {
        Document document = null;
        try {
            // parsing XML - document and creating tree structure
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();

            // gettig child elements <flower>
            NodeList flowerList = root.getElementsByTagName(ELEMENT_FLOWER);

            for (int i = 0; i < flowerList.getLength(); i++) {
                Element flowerElement = (Element) flowerList.item(i);
                Flower flower = buildFlower(flowerElement);
                this.flowers.add(flower);
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Flower buildFlower(Element flowerElement) {
        Flower flower = new Flower();
        Flower.VisualParam visualParam = new Flower.VisualParam();
        Flower.GrowingTips growingTips = new Flower.GrowingTips();

        // filling of object flower

        flower.setName(getElementTextContent(flowerElement, ELEMENT_NAME));
        flower.setSoil(getElementTextContent(flowerElement, ELEMENT_SOIL));
        flower.setOrigin(getElementTextContent(flowerElement, ELEMENT_ORIGIN));

        //Visual_param
        Double size = Double.parseDouble(getElementTextContent(flowerElement,
                ELEMENT_SIZE));
        visualParam.setSize(size);
        String color = (getElementTextContent(flowerElement,
                ELEMENT_COLOR));
        visualParam.setColor(color);
        String leafColor = (getElementTextContent(flowerElement,
                ELEMENT_LEAF_COLOR));
        visualParam.setLeafColor(leafColor);
        flower.setVisualParam(visualParam);

        //growing tips
        Double temperature = Double.parseDouble(getElementTextContent
                (flowerElement,
                        ELEMENT_TEMPERATURE));
        growingTips.setTemperature(temperature);

        Double lighting = Double.parseDouble(getElementTextContent
                (flowerElement,
                        ELEMENT_LIGHTING));
        growingTips.setLighting(lighting);

        Double watering = Double.parseDouble(getElementTextContent
                (flowerElement,
                        ELEMENT_WATERING));
        growingTips.setWatering(watering);

        flower.setGrowingTips(growingTips);

        flower.setMultiplying(getElementTextContent(flowerElement, ELEMENT_MULTIPLYING));

        return flower;
    }

    private static String getElementTextContent(Element element, String
            elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);

        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
