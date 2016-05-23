package fr.duminy.benchmarks.serialization.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public enum SerializerFactory {
    BASELINE {
        @Override public Serializer createSerializer() {
            return new Serializer() {
                @Override
                public ByteArrayOutputStream serialize(Object object) throws Exception {
                    return new ByteArrayOutputStream();
                }

                @Override
                public Object deserialize(ByteArrayInputStream inputStream) throws Exception {
                    return null;
                }
            };
        }
    },
    JDK {
        @Override public Serializer createSerializer() {
            return new JdkSerializer();
        }
    };

    abstract public Serializer createSerializer();
}
