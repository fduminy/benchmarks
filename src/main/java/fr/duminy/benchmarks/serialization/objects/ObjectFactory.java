package fr.duminy.benchmarks.serialization.objects;

public enum ObjectFactory {
    SMALL_OBJECT(Integer.class) {
        @Override public Integer createObject() {
            return 123456;
        }
    },
    BIG_OBJECT(BigClass.class) {
        @Override public BigClass createObject() throws InterruptedException {
            return new BigClass(10, 300000);
        }
    };

    private final Class<?> objectClass;

    ObjectFactory(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    @SuppressWarnings("unchecked")
    public final <T> Class<T> getObjectClass() {
        return (Class<T>) objectClass;
    }

    public abstract Object createObject() throws Exception;
}
