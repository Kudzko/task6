package by.epam.javawebtraining.kudzko.task06.model.logic;

import by.epam.javawebtraining.kudzko.task06.model.logic.dom.GreenHouseDOMBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.sax.GreenHouseSAXBuilder;
import by.epam.javawebtraining.kudzko.task06.model.logic.stax.MyStAXBuilder;

import java.util.HashMap;
import java.util.Map;

public class MyBuilderFactory {

   private Map<BuilderType, AbstractBuilder> builderMap;

    {
        builderMap = new HashMap<>();

        builderMap.put(BuilderType.DOM, GreenHouseDOMBuilder.getInstance());
        builderMap.put(BuilderType.SAX, GreenHouseSAXBuilder.getInstance());
        builderMap.put(BuilderType.STAX, MyStAXBuilder.getInstance());
    }

    public AbstractBuilder createBuilder(BuilderType type) {
        return builderMap.get(type);
    }

    public Map<BuilderType, AbstractBuilder> getBuilderMap() {
        return builderMap;
    }
}
