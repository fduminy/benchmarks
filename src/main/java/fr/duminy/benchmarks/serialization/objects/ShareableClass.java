package fr.duminy.benchmarks.serialization.objects;

public class ShareableClass extends BaseClass {
    private final Integer value;

    public ShareableClass(Integer value) {
        super(value.toString());
        this.value = value;
    }
}
