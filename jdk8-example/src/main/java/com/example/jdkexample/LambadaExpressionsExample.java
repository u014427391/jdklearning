package com.example.jdkexample;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class LambadaExpressionsExample {

    public static void main(String[] args) {
        // example 1 ：Runnable 任务 ，无参方式
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        Thread t = new Thread(r);
        t.start();

        // example 2:： Comparator 比较器 ，一个参数例子
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        list.add(2);
        // 常规比较
        list.sort((a,b)->{return a>b?-1:1;});
        // 第2种方法，使用comparingInt加上lambada表达式，升序排序
        list.sort(Comparator.comparingInt(a -> a));
        // 变量打印参数
        list.forEach(v -> {System.out.println(v);});

        // example 3: 集合的操作，多个参数例子
        Map<String, Integer> items = new HashMap<>(8);
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        items.forEach((k , v) -> {System.out.println(String.format("key : %s , value : %s" , k , v));});

        // example 4 : 函数接口应用
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

}
