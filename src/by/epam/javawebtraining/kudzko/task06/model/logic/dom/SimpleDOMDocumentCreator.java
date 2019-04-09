package by.epam.javawebtraining.kudzko.task06.model.logic.dom;

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

public class SimpleDOMDocumentCreator {

    public static void createDocument(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = documentBuilder.newDocument();
        String root = "Greenhouse";
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);

        for (int i = 0; i < 1; i++){

            String elementFlower = "Flower";
            Element elFlower = document.createElement(elementFlower);


            String elementName = "Name";
            Element elName = document.createElement(elementName);
            String textName = "Rose Red";
            elName.appendChild(document.createTextNode(textName));

            String elementSoil = "Soil";
            Element elSoil = document.createElement(elementSoil);
            String textSoil = "ground ground";
            elSoil.appendChild(document.createTextNode(textSoil));

            String elementOrigin = "Origin";
            Element elOrigin = document.createElement(elementOrigin);
            String textOrigin = "BELARUS";
            elOrigin.appendChild(document.createTextNode(textOrigin));

            String elementVisual_param = "Visual_param";
            Element elVisual_param = document.createElement(elementVisual_param);

            String elementSize = "Size";
            Element elSize = document.createElement(elementSize);
            String textSize = "70";
            elSize.appendChild(document.createTextNode(textSize));

            String elementColor = "Color";
            Element elColor = document.createElement(elementColor);
            String textColor = "RED";
            elColor.appendChild(document.createTextNode(textColor));

            String elementLeaf_color = "Leaf_color";
            Element elLeaf_color = document.createElement(elementLeaf_color);
            String textLeaf_color = "black green";
            elLeaf_color.appendChild(document.createTextNode(textLeaf_color));

            elVisual_param.appendChild(elSize);
            elVisual_param.appendChild(elColor);
            elVisual_param.appendChild(elLeaf_color);

            String elementGrowing_tips = "Growing_tips";
            Element elGrowing_tips = document.createElement(elementGrowing_tips);

            String elementTemperature = "Temperature";
            Element elTemperature = document.createElement(elementTemperature);
            String textTemperature = "20.0";
            elTemperature.appendChild(document.createTextNode(textTemperature));

            String elementLighting = "Lighting";
            Element elLighting = document.createElement(elementLighting);
            String textLighting = "3000";
            elLighting.appendChild(document.createTextNode(textLighting));

            String elementWatering = "Watering";
            Element elWatering = document.createElement(elementWatering);
            String textWatering = "10000";
            elWatering.appendChild(document.createTextNode(textWatering));

            elGrowing_tips.appendChild(elTemperature);
            elGrowing_tips.appendChild(elLighting);
            elGrowing_tips.appendChild(elWatering);

            elFlower.appendChild(elName);
            elFlower.appendChild(elSoil);
            elFlower.appendChild(elOrigin);
            elFlower.appendChild(elVisual_param);
            elFlower.appendChild(elGrowing_tips);

            rootElement.appendChild(elFlower);
        }

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(".\\src\\by\\epam\\javawebtraining\\kudzko\\task06" +
                    "\\inputoutputdata\\newGreenhose.xml"));

            transformer.transform(source,result );
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }



}
