package fr.duminy.benchmarks.serialization.objects;

import java.math.BigDecimal;

public class ComplexClass extends BaseClass {
    private final ShareableClass shared;
    private final BigDecimal value;

    public ComplexClass(ShareableClass shared, BigDecimal value) {
        super(value.toString());
        this.shared = shared;
        this.value = value;
    }
}
