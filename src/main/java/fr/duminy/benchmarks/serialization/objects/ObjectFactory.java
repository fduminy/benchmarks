package fr.duminy.benchmarks.serialization.objects;

public enum ObjectFactory {
    SMALL_OBJECT {
        @Override public Integer createObject() {
            return 123456;
        }
    },
    BIG_OBJECT {
        @Override public BigClass createObject() throws InterruptedException {
            return new BigClass(10, 300000);
        }
    };

    abstract public Object createObject() throws Exception;
}
