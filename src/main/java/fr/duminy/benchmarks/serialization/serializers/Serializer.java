package fr.duminy.benchmarks.serialization.serializers;

public interface Serializer<T> {
    int MAX_SERIALIZED_SIZE = Integer.MAX_VALUE;

    byte[] serialize(T object) throws Exception;

    T deserialize(byte[] inputStream) throws Exception;
}
