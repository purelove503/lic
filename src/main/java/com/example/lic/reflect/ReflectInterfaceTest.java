package com.example.lic.reflect;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author wy
 * @date 2020/6/16 17:32
 * @description
 */
public class ReflectInterfaceTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Test
    public void getSuperTest() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.example.lic.reflect.ReflectInterfaceTest");
        Class<?> superclass = clazz.getSuperclass();
        System.out.println("父类：" + superclass.getName());

        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("实现的接口有：");
        for (int i = 0; i < interfaces.length; i ++) {
            System.out.println(interfaces[i].getName());
        }
    }
}