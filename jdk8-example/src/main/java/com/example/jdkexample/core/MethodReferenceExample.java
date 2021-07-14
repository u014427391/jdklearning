package com.example.jdkexample.core;


import java.util.Arrays;
import java.util.function.BiFunction;

public class MethodReferenceExample {

    public MethodReferenceExample() {
    }

    public MethodReferenceExample(String msg) {
        System.out.println(String.format("参数打印:%s" , msg));
    }

    public static void main(String[] args) {
        // example 1：引用类的静态方法
        BiFunction<Integer , Integer , Integer> add = MethodReferenceExample::add;
        int pr1 = add.apply(5 , 20);
        System.out.println(String.format("两个数相加的和：%s" , pr1));

        // example 2：引用类的实例方法
        String[] strs = {"Apple", "Banana" , "Apricot","Cumquat", "Grape", "Lemon","Loquat","Mango"};
        Arrays.sort(strs , String::compareToIgnoreCase);
        Arrays.asList(strs).forEach(str -> {System.out.println(String.format("水果名称:%s", str));});

        // example 3：引用对象的实例方法
        MethodReferenceExample example = new MethodReferenceExample();
        MyFunctionalInterface functionalInterface = example::echo;
        functionalInterface.display();

        // example 4：构造方法的引用
        MyInterface myInterface = MethodReferenceExample::new;
        myInterface.display("Method reference to a constructor");

    }

    public static int add(int a , int b) {
        return a + b;
    }

    public void echo() {
        System.out.println("hello world");
    }

}

@FunctionalInterface
interface MyFunctionalInterface{
    void display();
}

@FunctionalInterface
interface MyInterface{
    void display(String msg);
}