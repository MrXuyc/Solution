package com.mrxyc.jdk8.defaultfunction;

public class DefaultFunctionTester {
    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }
}

/**
 * 接口可以有default 方法  静态方法
 */
interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    static void blowHorn() {
        System.out.println("按喇叭");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是四轮车!");
    }
}

class Car implements Vehicle, FourWheeler {
//    @Override
//    public void print() {
//        System.out.println("我是四轮汽车!");
//    }

    public void print() {
        Vehicle.super.print();
        System.out.println("我是四轮车!");
    }
}
