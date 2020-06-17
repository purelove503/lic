package com.example.lic.reflect;

import org.junit.Test;

/**
 * @author wy
 * @date 2020/6/16 17:32
 * @description
 */
public class TestReflect {

    /**
     * 通过一个对象获取完整的包名和类名
     *
     * @param
     * @author wy
     * @date 2020/6/16 17:43
     * @return:
     */
    @Test
    public void getClassNameTest() {
        System.out.println(new TestReflect().getClass().getName());
    }

    /**
     * 实例化Class类对象
     *
     * @param 
     * @author wy
     * @date 2020/6/16 17:44
     * @return: 
     */
    @Test
    public void createObjectTest() throws ClassNotFoundException {
        // 一般使用这种方式
        Class clazz1 = Class.forName("com.example.lic.reflect.TestReflect");
        Class clazz3 = new TestReflect().getClass();
        Class clazz2 = TestReflect.class;
        System.out.println(clazz1.getName());
        System.out.println(clazz2.getName());
        System.out.println(clazz3.getName());
    }
}