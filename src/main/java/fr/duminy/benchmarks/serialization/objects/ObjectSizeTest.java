package fr.duminy.benchmarks.serialization.objects;

import fr.duminy.benchmarks.serialization.serializers.SerializerFactory;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;

@RunWith(Theories.class)
public class ObjectSizeTest {
    @Theory
    public void estimate(ObjectFactory objectFactory, SerializerFactory serializerFactory) throws Exception {
        Object object = objectFactory.createObject();
        ByteArrayOutputStream baos = serializerFactory.createSerializer().serialize(object);
        System.out.printf("%s %s : %,d bytes%n", serializerFactory, objectFactory, baos.size());
    }
}
