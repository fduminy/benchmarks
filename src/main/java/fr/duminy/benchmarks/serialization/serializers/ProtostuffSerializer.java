package fr.duminy.benchmarks.serialization.serializers;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerializer<T> implements Serializer<T> {
    private final Class<T> clazz;

    public ProtostuffSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T object) throws Exception {
        Schema schema = RuntimeSchema.getSchema(object.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(MAX_SERIALIZED_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(object, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public T deserialize(byte[] input) throws Exception {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T object = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(input, object, schema);
        return object;
    }
}
