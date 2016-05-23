package fr.duminy.benchmarks.serialization.objects;

import java.io.Serializable;

abstract public class BaseClass implements Serializable {
    private final String value;

    public BaseClass(String value) {
        this.value = value;
    }
}
