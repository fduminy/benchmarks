package fr.duminy.benchmarks.serialization;

import fr.duminy.benchmarks.serialization.objects.ObjectFactory;
import fr.duminy.benchmarks.serialization.serializers.Serializer;
import fr.duminy.benchmarks.serialization.serializers.SerializerFactory;
import org.openjdk.jmh.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@State(Scope.Benchmark)
public class ObjectContainer {
    @Param(value = { "SMALL_OBJECT", "BIG_OBJECT" })
    private ObjectFactory objectFactory;

    @Param(value = { "BASELINE", "JDK" })
    private SerializerFactory serializerFactory;

    private Serializer serializer;
    private ByteArrayOutputStream serializedObject;
    private ByteArrayInputStream workingCopy;

    public ObjectContainer() {
    }

    @Setup(Level.Trial)
    public void initSerializedObject() throws Exception {
        Object object = objectFactory.createObject();
        serializer = serializerFactory.createSerializer(object.getClass());
        serializedObject = serializer.serialize(object);
    }

    @Setup(Level.Invocation)
    public void makeCopy() {
        workingCopy = new ByteArrayInputStream(serializedObject.toByteArray());
    }

    public ByteArrayInputStream getInputStream() {
        return workingCopy;
    }

    public Serializer getSerializer() {
        return serializer;
    }
}