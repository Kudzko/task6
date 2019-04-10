package by.epam.javawebtraining.kudzko.task06.model.logic;

import by.epam.javawebtraining.kudzko.task06.model.logic.dom.GreenHouseDOMBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.GreenHouseSAXBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.stax.MyStAXBuilder;

public class MyBuilderFactory {
    public enum ParserType {
        SAX, STAX, DOM
    }

    public AbstractBuilder createBuilder(ParserType type) {
        switch (type) {
            case SAX:
                return GreenHouseSAXBuilder.getInstance();
            case STAX:
                return MyStAXBuilder.getInstance();
            default:
                return GreenHouseDOMBuilder.getInstance();
        }
    }
}
