package com.example.lic.reflect;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/17 16:12
 * @description 通过反射机制调用某个类的方法
 */
public class ReflectCallMethodTest {

    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = Class.forName("com.example.lic.reflect.ReflectCallMethodTest");

        // 调用方法1
        Method reflect1 = clazz.getMethod("reflect1");
        reflect1.invoke(clazz.newInstance());

        // 调用方法2
        Method reflect2 = clazz.getMethod("reflect2", int.class, String.class);
        reflect2.invoke(clazz.newInstance(), 20, "张三");
    }

    public void reflect1() {
        System.out.println("方法1");
    }

    public void reflect2(int age, String name) {
        System.out.println("方法2");
        System.out.println("age: " + age + ", name: " + name);
    }
}