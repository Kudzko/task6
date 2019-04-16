package by.epam.javawebtraining.kudzko.task06.model.logic;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractBuilder {
    private Lock lock = new ReentrantLock();
    protected List<Flower> flowers;

    public AbstractBuilder() {

    }

    public AbstractBuilder(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    /**
     * Use to lock builder before one of threads get result
     * @return true if builder free
     * and false if builder busy
     */
    public boolean canUse(){
        return lock.tryLock();
    }

    public void releaseBuilder(){
        lock.unlock();
    }


    abstract public void buildListFlowers(String fileName);
}
