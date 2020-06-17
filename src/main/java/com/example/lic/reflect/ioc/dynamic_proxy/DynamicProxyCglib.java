package com.example.lic.reflect.ioc.dynamic_proxy;

import com.example.lic.reflect.ioc.interceptor.InterceptorHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wy
 * @date 2020/6/17 18:18
 * @description Cglib动态代理
 */
public class DynamicProxyCglib implements MethodInterceptor {

    private List<InterceptorHandler> handlerList;

    public DynamicProxyCglib(List<InterceptorHandler> handlerList) {
        this.handlerList = handlerList;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("自定义spring 代理类");

        Object result = methodProxy.invokeSuper(obj, args);
        return result;
    }
}