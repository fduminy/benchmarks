package fr.duminy.benchmarks.serialization.serializers;

public interface Serializer<T> {
    byte[] serialize(T object) throws Exception;

    T deserialize(byte[] inputStream) throws Exception;
}
