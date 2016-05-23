package fr.duminy.benchmarks.serialization;

import fr.duminy.benchmarks.serialization.objects.ObjectFactory;
import org.openjdk.jmh.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@State(Scope.Benchmark)
public class ObjectContainer {
    @Param(value = { "SMALL_OBJECT", "BIG_OBJECT" })
    private ObjectFactory objectFactory;

    private ByteArrayOutputStream serializedObject;
    private ByteArrayInputStream workingCopy;

    public ObjectContainer() {
    }

    @Setup(Level.Trial)
    public void initSerializedObject() throws Exception {
        serializedObject = Utils.serialize(objectFactory.createObject());
    }

    @Setup(Level.Invocation)
    public void makeCopy() {
        workingCopy = new ByteArrayInputStream(serializedObject.toByteArray());
    }

    ByteArrayInputStream getInputStream() {
        return workingCopy;
    }

}