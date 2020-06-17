package com.example.lic.reflect.ioc.dynamic_proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/17 18:18
 * @description Cglib动态代理
 */
public class DynamicProxyCglib implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("自定义spring 代理类");

        Object result = methodProxy.invokeSuper(obj, args);
        return result;
    }
}