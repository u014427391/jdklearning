package com.example.jdkexample.core.functioninterfaces;


import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacesExample {

    public static void main(String[] args) {

        // example 1：lambada表达式赋值给Runnable函数式接口
        Runnable r = () -> System.out.println("hello world");

        // example 2 : FunctionalInterface自定义接口
        AddInterface addInterface = (a , b) -> a + b;
        System.out.println(String.format("两个数字相加:%s" , addInterface.addMethod(10,24)));

        // example 3 : java.util.function函数接口应用
        // Consumer接口接收一个参数,不返回参数
        consumerFun(1 , v -> {System.out.println(v);});
        // binConsumer 接收两个参数,返回0个参数
        binConsumerFun("hello", "world" , (a ,b) -> {
            System.out.println(a + b);
        });
        // Predicate 接收一个参数,返回一个boolean值
        System.out.println(predicateFun(3 , a -> a ==3));
        // Predicate 接收0个参数,返回一个值
        System.out.println(supplierFun(() -> 1));
    }

    public static void consumerFun(int value, Consumer<Integer> c) {
        c.accept(value);
    }

    public static void binConsumerFun(String a , String b, BiConsumer<String , String> binc) {
        binc.accept(a , b);
    }

    public static boolean predicateFun(int value, Predicate<Integer> pre) {
        return pre.test(value);
    }

    public static int supplierFun(Supplier<Integer> supplier) {
        return supplier.get();
    }

    @FunctionalInterface
    interface AddInterface {
        int addMethod(int a , int b);
    }
}
