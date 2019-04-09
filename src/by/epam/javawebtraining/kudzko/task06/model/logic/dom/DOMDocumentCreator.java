package by.epam.javawebtraining.kudzko.task06.model.logic.dom;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;
import by.epam.javawebtraining.kudzko.task06.model.logic.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DOMDocumentCreator {
    public static final String ROOT = Constants.ROOT;
    public static final String ELEMENT_FLOWER = Constants.ELEMENT_FLOWER;
    public static final String ELEMENT_NAME = Constants.ELEMENT_NAME;
    public static final String ELEMENT_SOIL = Constants.ELEMENT_SOIL;
    public static final String ELEMENT_ORIGIN = Constants.ELEMENT_ORIGIN;
    public static final String ELEMENT_VISUAL_PARAM = Constants.ELEMENT_VISUAL_PARAM;
    public static final String ELEMENT_SIZE = Constants.ELEMENT_SIZE;
    public static final String ELEMENT_COLOR = Constants.ELEMENT_COLOR;
    public static final String ELEMENT_LEAF_COLOR = Constants.ELEMENT_LEAF_COLOR;
    public static final String ELEMENT_GRWING_TIPS = Constants.ELEMENT_GRWING_TIPS;
    public static final String ELEMENT_TEMPERATURE = Constants.ELEMENT_TEMPERATURE;
    public static final String ELEMENT_LIGHTING = Constants.ELEMENT_LIGHTING;
    public static final String ELEMENT_WATERING = Constants.ELEMENT_WATERING;
    public static final String ELEMENT_MULTIPLYING = Constants.ELEMENT_MULTIPLYING;
    public static final String OUT_PUT_DOCUMENT = Constants.OUT_PUT_DOCUMENT;

    public static void createDocument(List<Flower> flowerList) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(ROOT);
        document.appendChild(rootElement);

        for (int i = 0; i < flowerList.size(); i++) {
            Flower flower = flowerList.get(i);

            if (flower != null) {

                Element elFlower = document.createElement(ELEMENT_FLOWER);

                Element elName = document.createElement(ELEMENT_NAME);
                String textName = flower.getName();
                elName.appendChild(document.createTextNode(textName));

                Element elSoil = document.createElement(ELEMENT_SOIL);
                String textSoil = flower.getSoil();
                elSoil.appendChild(document.createTextNode(textSoil));

                Element elOrigin = document.createElement(ELEMENT_ORIGIN);
                String textOrigin = flower.getOrigin();
                elOrigin.appendChild(document.createTextNode(textOrigin));

                Element elVisual_param = document.createElement(ELEMENT_VISUAL_PARAM);
                Flower.VisualParam visualParam = flower.getVisualParam();
                if (visualParam != null) {

                    appendElementToElement(ELEMENT_SIZE, visualParam.getSize()
                            , document, elVisual_param);
                    appendElementToElement(ELEMENT_COLOR, visualParam.getColor()
                            , document, elVisual_param);
                    appendElementToElement(ELEMENT_LEAF_COLOR, visualParam.getLeafColor()
                            , document, elVisual_param);
                }

                Element elGrowing_tips = document.createElement(ELEMENT_GRWING_TIPS);
                Flower.GrowingTips growingTips = flower.getGrowingTips();
                if (growingTips != null) {
                    appendElementToElement(ELEMENT_TEMPERATURE, growingTips.getTemperature()
                            , document, elGrowing_tips);
                    appendElementToElement(ELEMENT_LIGHTING, growingTips.getLighting()
                            , document, elGrowing_tips);
                    appendElementToElement(ELEMENT_WATERING, growingTips.getWatering()
                            , document, elGrowing_tips);
                }

                Element elMultiplying = document.createElement(ELEMENT_MULTIPLYING);
                String textMultiplying = flower.getMultiplying();
                elMultiplying.appendChild(document.createTextNode(textMultiplying));

                elFlower.appendChild(elName);
                elFlower.appendChild(elSoil);
                elFlower.appendChild(elOrigin);
                elFlower.appendChild(elVisual_param);
                elFlower.appendChild(elGrowing_tips);
                elFlower.appendChild(elMultiplying);

                rootElement.appendChild(elFlower);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(OUT_PUT_DOCUMENT));

            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void appendElementToElement(String nameElement, String elementData,
                                               Document document, Element toElement) {
        Element element = document.createElement(nameElement);
        element.appendChild(document.createTextNode(elementData));
        toElement.appendChild(element);
    }

    private static void appendElementToElement(String nameElement, Double
            elementData, Document document, Element toElement) {
        Element element = document.createElement(nameElement);
        element.appendChild(document.createTextNode(elementData.toString()));
        toElement.appendChild(element);
    }

}

