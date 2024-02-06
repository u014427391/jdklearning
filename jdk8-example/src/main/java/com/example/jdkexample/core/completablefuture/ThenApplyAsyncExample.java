package com.example.jdkexample.core.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThenApplyAsyncExample {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> buyFuture = CompletableFuture.supplyAsync(() -> {
            return "code:123456";
        });

        CompletableFuture<Double> getPriceFuture = buyFuture.thenApplyAsync((code) -> {
            return getPrice(code);
        });
        getPriceFuture.thenAccept((result) -> {
            System.out.println("price:" + result);
        });

        TimeUnit.SECONDS.sleep(2);


    }

    static Double getPrice(String code) {
        System.out.println("商品编码：" + code);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        return Math.random() * 10;
    }
}
