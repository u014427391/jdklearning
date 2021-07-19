package com.example.jdkexample.core.streamapi;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.regex.Pattern.compile;

@Slf4j
public class StreamApiExample {

    public static void main(String[] args) {
        // 创建stream的方式
        //createStream();
        // intermediate Operations
        //intermediateOperations();
        //terminal Operations
        terminalOperations();
    }

    protected static void createStream(){
        // example : 从文件读取数据
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://javap.txt")));
        } catch (FileNotFoundException e) {
            // ignore exception
            log.error("FileNotFoundException :{}",e);
        }
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(s -> {System.out.println(s);});

        // example ：创建无限流
        // 无限等比数列
        Stream<Integer> columns = Stream.iterate(1 , n -> n*2);
        // 生成无限随机数流
        Stream<Double> nums = Stream.generate(Math::random);
        // 无限数值流
        IntStream ints = new Random().ints();

        // example：创建有限流
        IntStream.of(new int[]{1,2,3});
        IntStream.range(1,10);
        IntStream.rangeClosed(1,10);
        //使用Pattern 将字符串分隔成流
        Pattern pattern = compile(",");
        Stream<String> streams = pattern.splitAsStream("a , b , c , d , e");
        streams.forEach( System.out::print);
    }

    protected static void intermediateOperations() {
        // example :distinct 唯一
        List<String> distinctStrs = Stream.of("a", "b" , "c" , "d" , "e", "b")
                .distinct()
                .collect(Collectors.toList());
        System.out.println(String.format("distinct 列表数据：%s" , distinctStrs));

        // example : filter 过滤
        List<Integer> columns = Stream.of(1 ,2 ,-1,3,4,5,6,7,8,9)
                .filter(n -> n > 0)
                .collect(Collectors.toList());
        System.out.println(String.format("filter 列表数据：%s" , columns));

        // example : map 映射
        List<String[]> mapArras = Stream.of("hello","hi")
                .map(e -> e.split("") )
                .distinct()
                .collect(Collectors.toList());
        // List<String[]>类型的，不能直接打印
        mapArras.forEach(System.out::println);

        // example : flatMap 映射汇总
        List<String> mapStrs = Stream.of("hello","hi")
                .map(e -> e.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        // 通过.flatMap(Arrays::stream)转成string数据
        mapStrs.forEach(s->System.out.println(s));


        // example ：limit限制
        List<Integer> ints = IntStream.range(1,1000).limit(10)
                .boxed()
                .collect(Collectors.toList());
        // 打印[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println(ints);

        // example ：peek  观察者
        Arrays.stream(new String[]{"a","b","c","d","e"})
                // 每个元素被消费时都会执行这个钩子
                .peek(System.out::println)
                .count();

        //example ：sorted 排序
        List<Integer> sortedInts = Stream.of(1 ,9, 3, 2, 10,5,8)
                .sorted(
                        (a , b) -> {
                            return a >b ? 1 :-1;
                        }
                )
                //.sorted(Comparator.comparingInt(a -> a))
                .collect(Collectors.toList());
        System.out.println(sortedInts);

        // example : skip 跳过
        List<String> skipStrs = Stream.of("a", "b", "c", "d", "e","f","g","h","i")
                //丢弃了前n个元素的流，如果流中的元素小于或者等于n，则返回空的流
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(skipStrs);
    }

    protected static void terminalOperations() {
        // example : Match
        //打印true
        System.out.println(Stream.of(1,2,3,4,5).allMatch( i -> i > 0));
        //打印true
        System.out.println(Stream.of(1,2,3,4,5).anyMatch( i -> i > 0));
        //打印false
        System.out.println(Stream.of(1,2,3,4,5).noneMatch( i -> i > 0));

        // example ：count 计数
        String[] arr = new String[]{"a","b","c","d" , "e"};
        long count = Arrays.stream(arr).count();
        System.out.println(count);

        // example : collect 收集
        List<String> strs = Stream.of("a", "b" , "c" , "d" , "e", "b")
                .collect(Collectors.toList());
        System.out.println(strs);

        // example :max、min
        long minV = Stream.of(1,2,3,4,5).max(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                }
        ).get();
        long maxV = Stream.of(1,2,3,4,5).max(
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            }
        ).get();
        System.out.println(String.format("min value :%s , max value :%s", minV ,maxV));

        // example : reduce
        // reduce用于求和
        List<Integer> sumInts = Stream.of(1 ,2,3,4,5,6,7,8,9).collect(Collectors.toList());
        Optional<Integer> sum = sumInts.stream().reduce(Integer::sum);
        System.out.println(String.format("reduce计算的总值：%s" , sum));


        // example : toArray
        Integer[] integers = Stream.of(1 ,2,3,4,5,6,7,8,9).toArray(Integer[]::new);
        System.out.println(integers);

        // example : concat 组合
        List<String> list1 = Stream.of("a","b","c").collect(Collectors.toList());;
        List<String> list2 = Stream.of("d","e","f").collect(Collectors.toList());;
        Stream.concat(list1.stream() , list2.stream()).forEach(System.out::println);



    }


}
