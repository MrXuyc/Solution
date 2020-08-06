package com.mrxyc.jdk8.optional;

import java.util.Optional;

public class OptionalTester {
    public static void main(String[] args) {
        OptionalTester optionalTester = new OptionalTester();
        Integer value1 = null;
        Integer value2 = new Integer(10);
        Optional<Integer> optional = Optional.ofNullable(value1);
        Optional<Integer> value21 = Optional.of(value2);
        System.out.println(optionalTester.sum(optional, value21));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println(a.isPresent());
        System.out.println(b.isPresent());
        Integer integer1 = a.orElse(new Integer(0));
        final Integer integer = b.filter(x -> x == 20).orElse(new Integer(0));
        integer1 = a.orElseGet(() -> 10 + integer);
        return integer + integer1;
    }
}
