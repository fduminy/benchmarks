package fr.duminy.benchmarks.serialization.serializers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static fr.duminy.benchmarks.serialization.Utils.close;

public class JdkSerializer<T> implements Serializer<T> {
    @Override
    public ByteArrayOutputStream serialize(T object) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //TODO give a size
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } finally {
            close(oos);
        }
        return baos;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(ByteArrayInputStream inputStream) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            return (T) ois.readObject();
        } finally {
            close(ois);
        }
    }
}
