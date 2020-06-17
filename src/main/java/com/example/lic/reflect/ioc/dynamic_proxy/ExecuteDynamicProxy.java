package com.example.lic.reflect.ioc.dynamic_proxy;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @author wy
 * @date 2020/6/17 18:18
 * @description
 */
public class ExecuteDynamicProxy {

    /**
     * 获取代理类
     * TODO 目前只实现了一级代理，后期需要加入责任链
     *
     * @param o
     * @author wy
     * @date 2020/6/17 18:26
     * @return:
     */
    public static Object getProxy(Object o) {
        /**
         * 判断是否有接口
         */
        Class<?>[] interfaces = o.getClass().getInterfaces();
        if (null != interfaces && interfaces.length > 0) {
            // jdk代理
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(o.getClass());
            enhancer.setCallback(new DynamicProxyCglib());
            return enhancer.create();
        }
        return null;
    }
}