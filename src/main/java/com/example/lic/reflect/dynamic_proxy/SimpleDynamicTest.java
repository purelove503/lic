package com.example.lic.reflect.dynamic_proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author wy
 * @date 2020/6/17 16:34
 * @description
 */
public class SimpleDynamicTest {

    @Test
    public void test() {
        RealObject realObject = new RealObject();
        consumer(realObject);

        Interface o = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(realObject));
        consumer(o);
    }

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("boom");
    }
}