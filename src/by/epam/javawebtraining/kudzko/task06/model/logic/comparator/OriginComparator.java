package by.epam.javawebtraining.kudzko.task06.model.logic.comparator;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;

import java.util.Comparator;

public class OriginComparator implements Comparator<Flower> {

    @Override
    public int compare(Flower o1, Flower o2) {
        if ((o1 != null) && (o2 != null)){
            String origin1 = o1.getOrigin();
            String origin2 = o2.getOrigin();
            return origin1.compareTo(origin2);
        }else {
            return 0;
        }
    }
}
