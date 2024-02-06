package com.example.jdkexample.core.completablefuture;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadExample {


    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100));

        List<CompletableFuture<Integer>> futures = new ArrayList<>(10);


    }

}
