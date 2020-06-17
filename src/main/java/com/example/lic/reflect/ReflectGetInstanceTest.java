package com.example.lic.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wy
 * @date 2020/6/16 17:53
 * @description
 */
public class ReflectGetInstanceTest {
    /**
     * 获取某个类中的全部构造函数 - 详见下例
     * 通过反射机制实例化一个类的对象
     *
     * @param
     * @author wy
     * @date 2020/6/16 18:06
     * @return:
     */
    @Test
    public void getInstanceTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> class1 = null;
        class1 = Class.forName("com.example.lic.reflect.User");
        // 第一种方法，实例化默认构造方法，调用set赋值
        User user = (User) class1.newInstance();
        user.setAge(1);
        user.setName("张三");
        System.out.println("默认构造: " + user);

        // 第二种方法，取得全部的构造函数，使用构造函数赋值
        Constructor<?>[] cons = class1.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            Class<?>[] parameterTypes = cons[i].getParameterTypes();
            System.out.print("cons[" + i + "] (");
            for (int j = 0; j < parameterTypes.length; j ++) {
                System.out.print(parameterTypes[j].getName() + "; ");
            }
            System.out.println(")");
        }
        user = (User) cons[0].newInstance(20, "李四");
        System.out.println("0号构造：" + user);
        user = (User) cons[1].newInstance("王五");
        System.out.println("1号构造" + user);
    }
}