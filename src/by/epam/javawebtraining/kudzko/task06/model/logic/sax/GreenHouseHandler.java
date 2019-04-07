package by.epam.javawebtraining.kudzko.task06.model.logic.sax;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.entity.generated.FlowerEnum;
import by.epam.javawebtraining.kudzko.task06.model.logic.exception.logicexception.EnumConstantNotExistExceptoin;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreenHouseHandler extends DefaultHandler {

    private Set<Flower> flowers;
    private Flower current = null;
    private Flower.VisualParam currentVisualparam;
    private Flower.GrowingTips currentGrowingTips;
    private FlowerEnum currentEnum = null;
    private EnumSet<FlowerEnum> withText;

    public GreenHouseHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerEnum.NAME, FlowerEnum.MULTIPLYING);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("Flower".equals(localName)) {
            current = new Flower();

        } else if ("Visual_param".equals(localName)) {
            currentVisualparam = new Flower.VisualParam();
        }else if ("Growing_tips".equals(localName)) {
            currentGrowingTips = new Flower.GrowingTips();
        }else {
            FlowerEnum temp = FlowerEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("Flower".equals(localName)) {
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
                    currentVisualparam.setColor(s);
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
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
