package com.example.lic.reflect.ioc.dynamic_proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/17 18:18
 * @description Cglib动态代理
 */
@Slf4j
public class DynamicProxyWyDefaultCglib extends DynamicProxyWyDefault implements DynamicProxyCglibHandler {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("容器默代理方法");
        Object result = methodProxy.invokeSuper(obj, args);
        return result;
    }
}