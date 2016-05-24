package fr.duminy.benchmarks.serialization.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigClass implements Serializable {
    private final List<BaseClass> values;

    public BigClass(int nbSharedClass, int nbComplexClass) {
        values = new ArrayList<BaseClass>(nbSharedClass * (1 + nbComplexClass));
        int value = Short.MAX_VALUE + 1; // value high enough to avoid internal cache of Integers
        ShareableClass shared = new ShareableClass(new Integer(value++));
        for (int j = 0; j < nbComplexClass; j++) {
            values.add(new ComplexClass(shared, new BigDecimal(value++)));
        }
        values.add(shared);
    }
}
