package fr.duminy.benchmarks.serialization.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public enum SerializerFactory {
    BASELINE {
        @Override public <T> Serializer<T> createSerializer(Class<T> clazz) {
            return new Serializer<T>() {
                @Override
                public ByteArrayOutputStream serialize(T object) throws Exception {
                    return new ByteArrayOutputStream();
                }

                @Override
                public T deserialize(ByteArrayInputStream inputStream) throws Exception {
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
