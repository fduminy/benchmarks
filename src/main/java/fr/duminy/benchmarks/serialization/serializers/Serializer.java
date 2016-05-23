package fr.duminy.benchmarks.serialization.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public interface Serializer {
    ByteArrayOutputStream serialize(Object object) throws Exception;

    Object deserialize(ByteArrayInputStream inputStream) throws Exception;
}
