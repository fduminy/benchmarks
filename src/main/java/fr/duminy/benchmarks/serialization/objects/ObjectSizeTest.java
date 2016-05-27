package fr.duminy.benchmarks.serialization.objects;

import fr.duminy.benchmarks.serialization.serializers.Serializer;
import fr.duminy.benchmarks.serialization.serializers.SerializerFactory;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;

@RunWith(Theories.class)
public class ObjectSizeTest {
    @Theory
    @SuppressWarnings("unchecked")
    public <T> void estimate(ObjectFactory objectFactory, SerializerFactory serializerFactory) throws Exception {
        Object object = objectFactory.createObject();
        Serializer<T> serializer = (Serializer<T>) serializerFactory.createSerializer(object.getClass());
        Class<T> objectClass = objectFactory.getObjectClass();
        ByteArrayOutputStream baos = serializer.serialize(objectClass.cast(object));
        System.out.printf("%s %s : %,d bytes%n", serializerFactory, objectFactory, baos.size());
    }
}
