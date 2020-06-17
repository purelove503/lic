package com.example.lic.reflect.dynamic_proxy;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import com.example.lic.reflect.dynamic_proxy.annotations.Transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/17 16:30
 * @description
 */
public class DynamicProxyHandler implements InvocationHandler {

    // 被代理的类
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/*        System.out.print("代理类方法执行, proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.print("   " + arg);
            }
        }*/

        /**
         * 解析注解
         */
        Method realMethod = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
        Log log = realMethod.getAnnotation(Log.class);
        if (null != log) {
            if (log.before()) {
                System.out.println("日志, 入参, type1: " + log.type1() + ", type2: " + log.type2() + ", type3: " + log.type3()
                        + ", filter1: " + log.filter1() + ", filter2: " + log.filter2() + ", args: " + args);

            }
        }

        Transaction annotation = realMethod.getAnnotation(Transaction.class);
        if (annotation != null) {
            System.out.println("开启事务...");
        }

        Object result = method.invoke(proxied, args);

        /**
         * 出参日志
         */
        if (null != log) {
            if (log.after()) {
                System.out.println("日志, 入参, type1: " + log.type1() + ", type2: " + log.type2() + ", type3: " + log.type3()
                        + ", filter1: " + log.filter1() + ", filter2: " + log.filter2() + ", args: " + args + ", result: " + result);
            }
        }

        if (annotation != null) {
            System.out.println("提交事务...");
        }
        return result;
    }
}