package fr.duminy.benchmarks.serialization.objects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum ObjectFactory {
    SMALL_OBJECT {
        @Override public Integer createObject() {
            return 123456;
        }
    },
    BIG_OBJECT {
        @Override public List<BaseClass> createObject() throws InterruptedException {
            int nbSharedClass = 10;
            final int nbComplexClass = 300000;
            final List<BaseClass> values = new ArrayList<BaseClass>(nbSharedClass * (1 + nbComplexClass));
            int value = Short.MAX_VALUE + 1; // value high enough to avoid internal cache of Integers
            ShareableClass shared = new ShareableClass(new Integer(value++));
            for (int j = 0; j < nbComplexClass; j++) {
                values.add(new ComplexClass(shared, new BigDecimal(value++)));
            }
            values.add(shared);
            return values;
        }
    };

    abstract public Object createObject() throws Exception;
}
