package com.example.lic.reflect.ioc.dynamic_proxy;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/18 16:11
 * @description
 */
public interface DynamicProxyCglibHandler {

    Object intercept(Object var1, Method var2, Object[] var3, MethodProxy var4) throws Throwable;
}
