package fr.duminy.benchmarks.serialization;

import org.openjdk.jmh.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class ObjectContainer {
    @Param(value = { "SMALL_OBJECT", "BIG_OBJECT" })
    private ObjectFactory objectFactory;

    private ByteArrayOutputStream serializedObject;
    private ByteArrayInputStream workingCopy;

    public ObjectContainer() {
    }

    @Setup(Level.Trial)
    public void initSerializedObject() throws Exception {
        serializedObject = Utils.serialize(objectFactory.createObject());
    }

    @Setup(Level.Invocation)
    public void makeCopy() {
        workingCopy = new ByteArrayInputStream(serializedObject.toByteArray());
    }

    ByteArrayInputStream getInputStream() {
        return workingCopy;
    }

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

    abstract public static class BaseClass implements Serializable {
        private final String value;

        public BaseClass(String value) {
            this.value = value;
        }
    }

    public static class ShareableClass extends BaseClass {
        private final Integer value;

        public ShareableClass(Integer value) {
            super(value.toString());
            this.value = value;
        }
    }

    public static class ComplexClass extends BaseClass {
        private final ShareableClass shared;
        private final BigDecimal value;

        public ComplexClass(ShareableClass shared, BigDecimal value) {
            super(value.toString());
            this.shared = shared;
            this.value = value;
        }
    }
}