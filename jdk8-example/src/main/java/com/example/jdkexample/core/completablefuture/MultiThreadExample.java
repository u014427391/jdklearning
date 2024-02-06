package com.example.jdkexample.core.completablefuture;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MultiThreadExample {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100));

        List<CompletableFuture<Double>> futures = new ArrayList<>(10);

        IntStream.range(0,9).forEach(i -> {
            int index = i;
            CompletableFuture<Double> future = CompletableFuture
                    .supplyAsync(() -> buy(index), executorService)
                    .thenApplyAsync((code) -> transfer(index, code), executorService)
                    .exceptionally(e -> handleException(index, e));
            futures.add(future);
        });

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(5, TimeUnit.MINUTES);
        for (CompletableFuture<Double> future : futures) {
            Double aDouble = future.get();
            System.out.println("任务执行结果为：" + aDouble);
        }
        System.out.println("任务全部执行完成！");

    }


    static String buy(Integer index) {
        System.out.println("第"+index+"个任务执行下单");
        return "skuCode:"+"0000"+index;
    }

    static Double transfer(Integer index, String code) {
        System.out.println("第"+index+"个任务执行转账");
        System.out.println("商品编码:"+code);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        Double price = NumberUtil.round(RandomUtil.randomDouble (0,10000),1).doubleValue();
        System.out.println("金额:"+price);
        return price;
    }



    private static Double handleException(Integer index, Throwable e) {
        System.out.printf("第" + index +"个任务执行异常:"+e.getMessage());
        e.printStackTrace();
        return 0.0;
    }

}
