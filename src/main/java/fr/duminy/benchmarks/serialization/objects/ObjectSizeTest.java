package fr.duminy.benchmarks.serialization.objects;

import fr.duminy.benchmarks.serialization.Utils;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;

@RunWith(Theories.class)
public class ObjectSizeTest {
    @Theory
    public void estimate(ObjectFactory factory) throws Exception {
        Object object = factory.createObject();
        ByteArrayOutputStream baos = Utils.serialize(object);
        System.out.printf("%s : %d%n", factory, baos.size());
    }
}
