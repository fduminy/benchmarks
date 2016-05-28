package fr.duminy.benchmarks.serialization;

import fr.duminy.benchmarks.serialization.objects.ObjectFactory;
import fr.duminy.benchmarks.serialization.serializers.Serializer;
import fr.duminy.benchmarks.serialization.serializers.SerializerFactory;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ObjectContainer {
    @Param(value = { "SMALL_OBJECT", "BIG_OBJECT" })
    private ObjectFactory objectFactory;

    @Param(value = { "BASELINE", "JDK", "PROTOSTUFF" })
    private SerializerFactory serializerFactory;

    private Serializer serializer;
    private byte[] serializedObject;
    private byte[] workingCopy;

    public ObjectContainer() {
    }

    @SuppressWarnings("unchecked")
    @Setup(Level.Trial)
    public void initSerializedObject() throws Exception {
        Object object = objectFactory.createObject();
        serializer = serializerFactory.createSerializer(object.getClass());
        serializedObject = serializer.serialize(object);
    }

    @Setup(Level.Invocation)
    public void makeCopy() {
        workingCopy = serializedObject.clone();
    }

    public byte[] getInput() {
        return workingCopy;
    }

    public Serializer getSerializer() {
        return serializer;
    }
}