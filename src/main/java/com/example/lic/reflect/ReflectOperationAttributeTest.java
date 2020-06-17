package com.example.lic.reflect;

import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author wy
 * @date 2020/6/17 16:19
 * @description 通过反射机制操作某个类的属性
 */
@ToString
@Data
public class ReflectOperationAttributeTest {
    private String propertyPrivate = null;
    public String propertyPublic = null;

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.example.lic.reflect.ReflectOperationAttributeTest");
        Object o = clazz.newInstance();

        // 公共属性
        Field field = clazz.getField("propertyPublic");
        field.set(o, "公共属性");
        System.out.println(field.get(o));

        // 私有属性
        Field propertyPrivate = clazz.getDeclaredField("propertyPrivate");
        propertyPrivate.setAccessible(true);
        propertyPrivate.set(o, "私有属性");
        System.out.println(propertyPrivate.get(o));

        System.out.println(o);
    }
}