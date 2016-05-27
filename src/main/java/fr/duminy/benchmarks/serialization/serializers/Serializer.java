package fr.duminy.benchmarks.serialization.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public interface Serializer<T> {
    ByteArrayOutputStream serialize(T object) throws Exception;

    T deserialize(ByteArrayInputStream inputStream) throws Exception;
}
