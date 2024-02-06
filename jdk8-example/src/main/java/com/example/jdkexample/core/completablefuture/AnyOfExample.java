package com.example.jdkexample.core.completablefuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AnyOfExample {


    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<String> fromSina = CompletableFuture.supplyAsync(() -> {

            return "https://www.sina.com.cn/";
        });

        CompletableFuture<String> fromMoney = CompletableFuture.supplyAsync(() -> {
            return "https://money.163.com/";
        });

        CompletableFuture<Object> cfFuture = CompletableFuture.anyOf(fromSina, fromMoney);

        cfFuture.thenAccept((result) -> {
            System.out.println("result: " + result);
        });

        TimeUnit.SECONDS.sleep(2);


    }

}
