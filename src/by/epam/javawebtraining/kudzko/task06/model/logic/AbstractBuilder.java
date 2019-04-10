package by.epam.javawebtraining.kudzko.task06.model.logic;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder {

    protected List<Flower> flowers;

    public AbstractBuilder() {
        flowers = new ArrayList<>();
    }

    public AbstractBuilder(List<Flower> flowers) {
        this.flowers = flowers;
    }

    abstract public void buildListFlowers(String fileName);

    public List<Flower> getFlowers() {
        return flowers;
    }
}
