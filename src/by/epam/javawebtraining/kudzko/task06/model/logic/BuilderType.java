package by.epam.javawebtraining.kudzko.task06.model.logic;

import by.epam.javawebtraining.kudzko.task06.model.logic.dom.GreenHouseDOMBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.GreenHouseSAXBuilder;

public enum BuilderType {
    SAX, STAX, DOM;
}

/*
public enum BuilderType {
    SAX(GreenHouseSAXBuilder.getInstance()), STAX(GreenHouseDOMBuilder.getInstance()), DOM
            (GreenHouseSAXBuilder.getInstance());
    private AbstractBuilder abstractBuilder;

    BuilderType(AbstractBuilder abstractBuilder) {
        this.abstractBuilder = abstractBuilder;
    }
}
 */
