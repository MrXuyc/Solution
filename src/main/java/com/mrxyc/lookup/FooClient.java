package com.mrxyc.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class FooClient {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = Foo.lookup();
        Method bar = Foo.class.getDeclaredMethod("bar", Object.class);
        Method cat = Foo.class.getDeclaredMethod("cat", String.class, String.class, String.class);
        MethodHandle methodHandle = lookup.unreflect(bar);
        MethodHandle catMethodHandle = lookup.unreflect(cat);
        catMethodHandle.invoke("3", "2", "1");
        methodHandle.invoke(new Object());
        //参数类型必须相等 不能多态
        //methodHandle.invokeExact(new String());
        //改
        //methodHandle.asType(MethodType.methodType(String.class));
        //删
        MethodHandle methodHandle1 = MethodHandles.dropArguments(catMethodHandle, 0, String.class);
        methodHandle1.invoke("", "1", "2", "3");
        //增加参数
        MethodHandle methodHandle2 = catMethodHandle.bindTo("123");
        methodHandle2.invoke("4", "5");

        MethodType methodType = MethodType.methodType(void.class, Object.class);
        MethodType catMethodType = MethodType.methodType(void.class, String.class, String.class, String.class);
        MethodHandle handle = lookup.findStatic(Foo.class, "bar", methodType);
        MethodHandle catHandle = lookup.findStatic(Foo.class, "cat", catMethodType);
        catHandle.invoke("1", "2", "3");
        //删
        MethodHandle methodHandle11 = MethodHandles.dropArguments(catHandle, 2, String.class);
        methodHandle11.invoke("", "1", "2", "3");
        //增加参数
        MethodHandle methodHandle22 = catHandle.bindTo("123");
        methodHandle22.invoke("4", "5");

        handle.invoke(new Object());
    }
}
