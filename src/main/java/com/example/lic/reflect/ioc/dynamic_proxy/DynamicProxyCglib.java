package com.example.lic.reflect.ioc.dynamic_proxy;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import com.example.lic.reflect.ioc.interceptor.InterceptorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.testng.collections.Lists;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wy
 * @date 2020/6/18 11:46
 * @description
 */
@Slf4j
public class DynamicProxyCglib extends InterceptorHandler implements MethodInterceptor {

    public List<DynamicProxyCglibHandler> handlers;

    public DynamicProxyCglib(Object proxied, List<DynamicProxyCglibHandler> handlers) {
        super(proxied);
        this.handlers = handlers;
    }

    @Override
    public Boolean canInterceptor(Class<?> clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (null == declaredMethods || declaredMethods.length <= 0) {
            return false;
        }

        boolean result = false;
        for (Method method : declaredMethods) {
            if (method.getAnnotation(Log.class) != null) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("aop");

        /**
         * TODO 这里需要责任链的形式处理拦截器
         */

        Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}