package com.example.lic.reflect.ioc.dynamic_proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/17 18:18
 * @description Cglib动态代理
 */
@Slf4j
public class DynamicProxyLogCglib extends DynamicProxyLog implements DynamicProxyCglibHandler {

    /**
     * 责任链形式
     */
    public DynamicProxyCglibHandler dynamicProxyCglibHandler;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        super.pringInParameter(method, args, obj);
        Object result = methodProxy.invokeSuper(obj, args);
        super.pringOutParameter(method, args, result, obj);
        return result;
    }
}