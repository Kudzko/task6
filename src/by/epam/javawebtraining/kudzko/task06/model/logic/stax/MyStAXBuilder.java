package by.epam.javawebtraining.kudzko.task06.model.logic.stax;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.entity.generated.FlowerEnum;
import by.epam.javawebtraining.kudzko.task06.model.logic.AbstractBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.GreenHouseSAXBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyStAXBuilder extends AbstractBuilder {
    private volatile static MyStAXBuilder instance;
    private XMLInputFactory inputFactory;

    private MyStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    private MyStAXBuilder(List<Flower> flowers) {
        super(flowers);
    }

    public static MyStAXBuilder getInstance() {
        if (instance == null) {
            synchronized (AbstractBuilder.class) {
                if (instance == null) {
                    instance = new MyStAXBuilder();
                }
            }
        }
        return instance;
    }

    public static MyStAXBuilder getInstance(List<Flower> flowers) {
        if (instance == null) {
            synchronized (AbstractBuilder.class) {
                if (instance == null) {
                    instance = new MyStAXBuilder(flowers);
                }
            }
        }
        return instance;
    }


    @Override
    public void buildListFlowers(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()).equals(FlowerEnum.FLOWER)) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Flower buildFlower(XMLStreamReader reader) throws XMLStreamException {
        Flower flower = new Flower();

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            flower.setName(getXMLText(reader));
                            break;
                        case SOIL:
                            flower.setSoil(getXMLText(reader));
                            break;
                        case ORIGIN:
                            flower.setOrigin(getXMLText(reader));
                            break;
                        case VISUAL_PARAM:
                            flower.setVisualParam(getXMLVisualParam(reader));
                            break;
                        case GROWING_TIPS:
                            flower.setGrowingTips(getXMLGrowingTips(reader));
                            break;
                        case MULTIPLYING:
                            flower.setMultiplying(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()).equals(FlowerEnum.FLOWER)) {
                        return flower;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    private Flower.VisualParam getXMLVisualParam(XMLStreamReader reader) throws
            XMLStreamException {
        Flower.VisualParam visualParam = new Flower.VisualParam();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case SIZE:
                            name = getXMLText(reader);
                            visualParam.setSize(Double.parseDouble(name));
                            break;
                        case COLOR:
                            visualParam.setColor(getXMLText(reader));
                            break;
                        case LEAF_COLOR:
                            visualParam.setLeafColor(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()).equals(FlowerEnum
                            .VISUAL_PARAM)) {
                        return visualParam;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Visual_param");
    }

    private Flower.GrowingTips getXMLGrowingTips(XMLStreamReader reader) throws
            XMLStreamException {
        Flower.GrowingTips growingTips = new Flower.GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase())) {
                        case TEMPERATURE:
                            name = getXMLText(reader);
                            growingTips.setTemperature(Double.parseDouble(name));
                            break;
                        case LIGHTING:
                            name = getXMLText(reader);
                            growingTips.setLighting(Double.parseDouble(name));
                            break;
                        case WATERING:
                            name = getXMLText(reader);
                            growingTips.setWatering(Double.parseDouble(name));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase()) == FlowerEnum
                            .GROWING_TIPS) {
                        return growingTips;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Growing_tips");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

