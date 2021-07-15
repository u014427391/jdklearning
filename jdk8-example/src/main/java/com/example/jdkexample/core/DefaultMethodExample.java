package com.example.jdkexample.core;
interface TestInterface {
    default void newMethod(){
        System.out.println("This is a new method!");
    }
    static void anotherNewMethod(){
        System.out.println("This is a static method");
    }
    void otherMethod(String str);
}

public class DefaultMethodExample implements TestInterface {

    public static void main(String[] args) {
        DefaultMethodExample example = new DefaultMethodExample();
        example.newMethod();
        TestInterface.anotherNewMethod();
        example.otherMethod("hello world");
    }

    @Override
    public void otherMethod(String str) {
        System.out.println(String.format("echo：%s" , str));
    }
}
