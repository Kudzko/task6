package by.epam.javawebtraining.kudzko.task06.model.logic.sax;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.entity.generated.FlowerEnum;
import by.epam.javawebtraining.kudzko.task06.model.logic.Constants;
import by.epam.javawebtraining.kudzko.task06.model.logic.exception.logicexception.EnumConstantNotExistExceptoin;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class GreenHouseHandler extends DefaultHandler {
    public static final String ELEMENT_FLOWER = Constants.ELEMENT_FLOWER;

    private static GreenHouseHandler instance;

    private List<Flower> flowers;
    private Flower current = null;
    private Flower.VisualParam currentVisualparam;
    private Flower.GrowingTips currentGrowingTips;
    private FlowerEnum currentEnum = null;
    private EnumSet<FlowerEnum> withText;

    private GreenHouseHandler() {
        flowers = new ArrayList<>();
        withText = EnumSet.range(FlowerEnum.NAME, FlowerEnum.MULTIPLYING);
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public static GreenHouseHandler getInstance(){
        if ( instance == null){
            synchronized (GreenHouseHandler.class){
                if (instance == null){
                    instance = new GreenHouseHandler();
                }
            }
        }
        return instance;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (ELEMENT_FLOWER.equals(localName)) {
            current = new Flower();
            currentVisualparam = new Flower.VisualParam();
            currentGrowingTips = new Flower.GrowingTips();
        } else {
            FlowerEnum temp = FlowerEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (ELEMENT_FLOWER.equals(localName)) {
            flowers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case SOIL:
                    current.setSoil(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case VISUAL_PARAM:
                    current.setVisualParam(currentVisualparam);
                    break;

                case SIZE:
                    currentVisualparam.setSize(Double.parseDouble(s));
                    break;
                case COLOR:
                    currentVisualparam.setColor(s);
                    break;
                case LEAF_COLOR:
                    currentVisualparam.setLeafColor(s);
                    break;

                case GROWING_TIPS:
                    current.setGrowingTips(currentGrowingTips);
                    break;
                case TEMPERATURE:
                    currentGrowingTips.setTemperature(Double.parseDouble(s));
                    break;
                case LIGHTING:
                    currentGrowingTips.setLighting(Double.parseDouble(s));
                    break;
                case WATERING:
                    currentGrowingTips.setWatering(Double.parseDouble(s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException
                            (currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

    @Override
    public void startDocument() throws SAXException {
        flowers = new ArrayList<>();
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
