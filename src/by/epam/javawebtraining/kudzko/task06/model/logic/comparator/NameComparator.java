package by.epam.javawebtraining.kudzko.task06.model.logic.comparator;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;

import java.util.Comparator;

public class NameComparator implements Comparator<Flower> {

    @Override
    public int compare(Flower o1, Flower o2) {
        if ((o1 != null) && (o2 != null)){
            String name1 = o1.getName();
            String name2 = o2.getName();
            return name1.compareTo(name2);
        }else {
            return 0;
        }


    }
}
