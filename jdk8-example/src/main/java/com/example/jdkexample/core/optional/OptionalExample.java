package com.example.jdkexample.core.optional;


import com.example.jdkexample.core.optional.entity.Address;
import com.example.jdkexample.core.optional.entity.City;
import com.example.jdkexample.core.optional.entity.User;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        //exampleOfOptional();

        City city = new City("上海");
        Address address = new Address("200000",city);
        User userInfo = new User("jack", "15588899988" , "123@foxmail.com", address,"Developer");
        System.out.println(getCityName(userInfo));
        System.out.println(obtainCityName(userInfo));

    }

    protected static void exampleOfOptional(){
        // example : 创建Optional实例
        // 使用 empty() 方法创建一个空的 Optional
        Optional<User> empOpt = Optional.empty();
        // 使用 of() 方法创建包含值的 Optional，不可以传入一个null值
        User user = new User();
        Optional<User> opt = Optional.of(user);
        //使用 ofNullable() 方法创建包含值的 Optional，可以传入一个null值
        User userObj = null;
        Optional<User> userOptional = Optional.ofNullable(userObj);

        // example : 获取Optional对象的值
        // 直接使用get()获取值，value值为null会抛异常
        String name = "jack";
        Optional<String> strOpt = Optional.ofNullable(name);
        String getStr = strOpt.get();
        System.out.println(getStr);
        // 使用ifPresent，避免userInfo值为null的情况
        City city = new City("上海");
        Address address = new Address("200000",city);
        User userInfo = new User("jack", "15588899988" , "123@foxmail.com", address,"");
        Optional.ofNullable(userInfo).ifPresent(us -> System.out.println(us.toString()));
        // 不太建议这种写法，从语法上来说是可以的，不过设计者的目的是使用表达式语法简洁java语法
        Optional<User> userOptiion = Optional.ofNullable(userInfo);
        if (userOptiion.isPresent()) {
            User userInfomation = userOptiion.get();
        }

        // example ：Optional返回默认值
        //使用 orElse() 返回默认值，如果有值则返回该值，没数据返回默认值
        User tUser = null;
        System.out.println("using orElse");
        tUser = Optional.ofNullable(tUser).orElse(defaultUserInfo());
        System.out.println("default user information："+tUser.toString());
        // 使用 orElseGet() 返回默认值 ，这个方法会在有值的时候返回值，如果没有值，
        // 它会执行作为参数传入的 Supplier(供应者) 函数式接口
        User teUser = null;
        System.out.println("using orElseGet");
        teUser = Optional.ofNullable(teUser).orElseGet(() -> defaultUserInfo());
        System.out.println("default user information："+teUser.toString());

        // example : Optional返回异常
        User u = new User();
        User userData = Optional.ofNullable(u).orElseThrow(() -> new IllegalArgumentException());

        // example : Optional转换值
        User us = new User("tom", "15588899988" , "123@foxmail.com", null,"");
        String email = Optional.ofNullable(us).map(userInfor -> userInfor.getEmail()).orElse("defaulEmail@foxmail.com");
        String position = Optional.ofNullable(us).flatMap(use -> use.getPosition()).orElse("default");

        // example : Optional过滤值
        Optional<User> uoptional = Optional.ofNullable(us).filter(euser -> euser.getEmail()!=null && euser.getEmail().contains("@"));
        System.out.println(uoptional.get().toString());
    }

    protected static User defaultUserInfo(){
        System.out.println("create default user information!");
        City city = new City("上海");
        Address address = new Address("200000",city);
        User userInfo = new User("jack", "15588899988" , "123@foxmail.com", address,"");
        return userInfo;
    }

    /**
     * the example of jdk7 getCityName .<br>
     * @Author  nicky.ma
     * @Date 2021/07/20 14:39
     * @Param [user]
     * @return java.lang.String
     */
    protected static String getCityName(User user) {
        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                City city = address.getCity();
                if (city != null) {
                    return city.getCityName();
                }
            }
        }
        throw new IllegalArgumentException("取值错误");
    }

    /**
     * the example of jdk8 getCityName .<br>
     * @Author nicky.ma
     * @Date 2021/07/20 14:38
     * @Param [user]
     * @return java.lang.String
     */
    protected static String obtainCityName(User user) {
        return Optional.ofNullable(user)
                .map(u -> u.getAddress())
                .map(a -> a.getCity())
                .map(c -> c.getCityName())
                .orElseThrow(() -> new IllegalArgumentException("取值错误"));
    }



}
