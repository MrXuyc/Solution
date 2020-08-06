package com.mrxyc.jdk8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 1、参数不允许 同名局部变量 2、外部参数 需要final 或者局部隐式final
 */
public class LambdaTester {
    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    final static String salutation = "Hello! ";

    final static List<String> salutations = Arrays.asList("1", "2");

    public static void main(String[] args) {
        LambdaTester lambdaTester = new LambdaTester();
        MathOperation addition = (int a, int b) -> {
            return a + b;
        };
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> {
            return a * b;
        };
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + lambdaTester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambdaTester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lambdaTester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambdaTester.operate(10, 5, division));
        GreetingService greetingService = message -> {
            System.out.println("Hello" + message);
        };
        GreetingService greetingService1 = (message) -> System.out.println("Hello" + message);
        greetingService.sayMessage("yanchun");
        greetingService1.sayMessage("qiongyi");
        GreetingService greetingService2 = (message) -> System.out.println(salutation + message);
        greetingService1.sayMessage("yibao");
        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
        //num = 5;
        salutations.add("3");
        salutations.toArray();
    }
}
