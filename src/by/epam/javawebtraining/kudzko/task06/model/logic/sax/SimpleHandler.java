package by.epam.javawebtraining.kudzko.task06.model.logic.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleHandler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        System.out.println("Parsing started");
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // super.startElement(uri, localName, qName, attributes);

        String s = localName;
        // getting and output information about element
        for (int i = 0; i < attributes.getLength(); i++) {
            s += " " + attributes.getLocalName(i) + "=" + attributes.getValue
                    (i);
        }
        System.out.print(s.trim());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length);
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //super.endElement(uri, localName, qName);
        System.out.print(localName);
    }

    @Override
    public void endDocument() throws SAXException {
        // super.endDocument();

        System.out.println("\nParsing ended");
    }
}
