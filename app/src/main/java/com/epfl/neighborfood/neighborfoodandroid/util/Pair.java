package com.epfl.neighborfood.neighborfoodandroid.util;


public class Pair<U, V> {
    private final U first;       // the first field of a triplet
    private final V second;      // the second field of a triplet

    // Constructs a new triplet with the given values
    private Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    // Factory method for creating a typed immutable instance of triplet
    public static <U, V> Pair<U, V> of(U a, V b) {
        return new Pair<>(a, b);
    }
}
