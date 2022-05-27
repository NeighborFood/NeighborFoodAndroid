package com.epfl.neighborfood.neighborfoodandroid.util;

// Triplet class
public class Triplet<U, V, T>
{
    private final U first;       // the first field of a triplet
    private final V second;      // the second field of a triplet
    private final T third;       // the third field of a triplet

    // Constructs a new triplet with the given values
    private Triplet(U first, V second, T third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    // Factory method for creating a typed immutable instance of triplet
    public static <U, V, T> Triplet <U, V, T> of(U a, V b, T c) {
        return new Triplet <>(a, b, c);
    }
}
