package com.mrxyc.lookup;

import java.lang.invoke.MethodHandles;

public class Foo {

    private static void bar(Object o) {
        System.out.println("bar");
    }

    private static void cat(String o1, String o2, String o3) {
        System.out.println(o1 + o2 + o3);
    }

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

}
