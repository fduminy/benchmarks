package fr.duminy.benchmarks.serialization.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static fr.duminy.benchmarks.serialization.Utils.close;

public class JdkSerializer<T> implements Serializer<T> {
    @Override
    public byte[] serialize(T object) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(MAX_SERIALIZED_SIZE);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } finally {
            close(oos);
        }
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(byte[] input) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(input));
            return (T) ois.readObject();
        } finally {
            close(ois);
        }
    }
}
