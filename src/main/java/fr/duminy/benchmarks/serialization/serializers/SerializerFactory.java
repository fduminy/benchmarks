package fr.duminy.benchmarks.serialization.serializers;

public enum SerializerFactory {
    BASELINE {
        @Override public <T> Serializer<T> createSerializer(Class<T> clazz) {
            return new Serializer<T>() {
                @Override
                public byte[] serialize(T object) throws Exception {
                    return new byte[0];
                }

                @Override
                public T deserialize(byte[] input) throws Exception {
                    return null;
                }
            };
        }
    },
    JDK {
        @Override public <T> Serializer<T> createSerializer(Class<T> clazz) {
            return new JdkSerializer<T>();
        }
    };

    public abstract <T> Serializer<T> createSerializer(Class<T> clazz);
}
