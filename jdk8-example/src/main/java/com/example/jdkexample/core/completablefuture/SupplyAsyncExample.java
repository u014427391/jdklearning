package com.example.jdkexample.core.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class SupplyAsyncExample {


    public static void main(String[] args) throws InterruptedException, ExecutionException {



        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(SupplyAsyncExample::getPrice);
        cf.thenAccept((result) -> {
            System.out.println("price:" + result);
        });
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return 0.0;
        });
        // 主线程睡眠一下，避免CompletableFuture默认使用的线程池立即关闭
        TimeUnit.SECONDS.sleep(2);


    }

    static Double getPrice() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        return Math.random() * 10;
    }
}
