package fr.duminy.benchmarks.serialization.serializers;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.PrintingVisitor;
import fr.duminy.benchmarks.serialization.objects.ObjectFactory;
import org.junit.Rule;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.File;

import static fr.duminy.benchmarks.serialization.serializers.SerializerFactory.BASELINE;
import static java.lang.System.out;
import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

@RunWith(Theories.class)
public class SerializerTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Theory
    public void testSerializeAndDeSerialize(ObjectFactory objectFactory, SerializerFactory serializerFactory)
        throws Exception {
        assumeTrue(!BASELINE.equals(serializerFactory));

        final Object expected = objectFactory.createObject();
        final Object actual = deserializeFromFile(serializerFactory, expected.getClass(),
                                                  serializeToFile(serializerFactory, expected));
        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(actual, expected);
        boolean notEquals = diff.hasChanges();
        if (notEquals) {
            diff.visit(new PrintingVisitor(expected, actual));
        }
        out.printf("objectFactory=%13s serializerFactory=%13s : serialize -> deserialize %s%n", objectFactory,
                   serializerFactory, notEquals ? "FAILURE" : "SUCCESS");
        assertThat(notEquals).isFalse();
    }

    @SuppressWarnings("unchecked")
    private <T> byte[] serialize(SerializerFactory serializerFactory, T object) throws Exception {
        Serializer<T> serializer = (Serializer<T>) serializerFactory.createSerializer(object.getClass());
        return serializer.serialize(object);
    }

    @SuppressWarnings("unchecked")
    private <T> T deserialize(SerializerFactory serializerFactory, Class<T> objectClass, byte[] serialized)
        throws Exception {
        Serializer<T> serializer = serializerFactory.createSerializer(objectClass);
        return serializer.deserialize(serialized);
    }

    private <T> File serializeToFile(SerializerFactory serializerFactory, T object) throws Exception {
        File file = temporaryFolder.newFile();
        writeByteArrayToFile(file, serialize(serializerFactory, object));
        return file;
    }

    private <T> T deserializeFromFile(SerializerFactory serializerFactory, Class<T> objectClass, File file)
        throws Exception {
        return deserialize(serializerFactory, objectClass, readFileToByteArray(file));
    }
}