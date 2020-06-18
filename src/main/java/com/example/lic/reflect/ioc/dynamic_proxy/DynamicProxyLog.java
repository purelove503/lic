package com.example.lic.reflect.ioc.dynamic_proxy;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author wy
 * @date 2020/6/18 11:46
 * @description
 */
@Slf4j
public abstract class DynamicProxyLog {

    /**
     * 打印入参
     *
     * @param method
     * @param args
     * @author wy
     * @date 2020/6/18 15:34
     * @return:
     */
    protected void pringInParameter(Method method, Object[] args, Object proxied) {
        try {
            Method realMethod = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
            Log loger = realMethod.getAnnotation(Log.class);
            if (null != loger) {
                if (loger.before()) {
                    System.out.println("日志, 入参, type1: " + loger.type1() + ", type2: " + loger.type2() + ", type3: " + loger.type3()
                            + ", filter1: " + loger.filter1() + ", filter2: " + loger.filter2() + ", args: " + args);
                }
            }
        } catch (Exception e) {
            log.error("打印日志出现异常, method: {}, proxied:{}", method, proxied, e);
        }
    }

    /**
     * 打印出参
     *
     * @param method
     * @param args
     * @param result
     * @author wy
     * @date 2020/6/18 15:34
     * @return:
     */
    protected void pringOutParameter(Method method, Object[] args, Object result, Object proxied) {
        /**
         * 出参日志
         */
        try {
            Method realMethod = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
            Log loger = realMethod.getAnnotation(Log.class);
            if (null != loger) {
                if (loger.after()) {
                    System.out.println("日志, 入参, type1: " + loger.type1() + ", type2: " + loger.type2() + ", type3: " + loger.type3()
                            + ", filter1: " + loger.filter1() + ", filter2: " + loger.filter2() + ", args: " + args + ", result: " + result);
                }
            }
        } catch (Exception e) {
            log.error("打印日志出现异常, method: {}, proxied:{}", method, proxied, e);
        }
    }
}