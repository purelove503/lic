package com.example.lic.reflect.ioc;

import com.example.lic.reflect.ioc.annotations.WyComponent;
import org.junit.Test;
import org.testng.collections.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wy
 * @date 2020/6/17 17:32
 * @description
 */
public class IocMain {

    public static final Map<String, Object> iocMap = new HashMap<>();

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        ScanningFile scanningFile = new ScanningFile();
        List<Class<?>> aClass = scanningFile.getClass("com.example.lic.reflect.ioc");

        List<Class<?>> objects = Lists.newArrayList();
        objects.add(WyComponent.class);
        RegisterHandler registerHandler = new RegisterHandler(objects);
        registerHandler.register(iocMap, aClass);

        System.out.println("初始化容器完毕");
    }
}