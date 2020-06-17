package com.example.lic.reflect.ioc.dynamic_proxy;

import com.example.lic.reflect.ioc.interceptor.InterceptorHandler;
import org.springframework.cglib.proxy.Enhancer;
import org.testng.collections.Lists;

import java.util.List;

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
        if (null == o) {
            return null;
        }

        /**
         * 筛选拦截器 AOP
         */
        List<InterceptorHandler> handlers = Lists.newArrayList();
        // TODO 判断哪些拦截器符合条件


        /**
         * 判断是否有接口
         */
        Class<?>[] interfaces = o.getClass().getInterfaces();
        if (null != interfaces && interfaces.length > 0) {
            // jdk代理
        } else {

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(o.getClass());
            enhancer.setCallback(new DynamicProxyCglib(handlers));
            return enhancer.create();
        }
        return null;
    }
}