package by.epam.javawebtraining.kudzko.task06.model.logic;

import by.epam.javawebtraining.kudzko.task06.model.entity.generated.Flower;

import java.util.Comparator;
import java.util.List;

public class Sorter {
    public static void sort(List<Flower> flowers, Comparator<Flower>...
            comparator) {
        if ((flowers != null) && (comparator != null)){
            for (int i = 0; i < comparator.length; i++) {
                flowers.sort(comparator[i]);
            }
        }
    }


}
