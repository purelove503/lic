package com.example.lic.reflect.ioc.dynamic_proxy;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import com.example.lic.reflect.ioc.annotations.WyAutoware;
import com.example.lic.reflect.ioc.annotations.WyComponent;
import com.example.lic.reflect.ioc.interceptor.InterceptorHandler;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wy
 * @date 2020/6/17 18:18
 * @description
 */
public class ExecuteDynamicProxy {

    /**
     * 代理拦截器
     */
    private static Map<Class<? extends DynamicProxyCglibHandler>, List<Class<? extends Annotation>>> map = Maps.newHashMap();
    static {
        List<Class<? extends Annotation>> objects = Lists.newArrayList();
        objects.add(Log.class);
        map.put(DynamicProxyLogCglib.class, objects);


        map.put(DynamicProxyWyDefaultCglib.class, Arrays.asList(Log.class, WyComponent.class, WyAutoware.class));
    }

    /**
     * 获取代理类
     * TODO 目前只实现了一级代理，后期需要加入责任链
     *
     * @param o
     * @author wy
     * @date 2020/6/17 18:26
     * @return:
     */
    public static Object getProxy(Object o) throws Exception {
        if (null == o) {
            return null;
        }

        /**
         * 判断是否有接口
         */
        Class<?>[] interfaces = o.getClass().getInterfaces();
        if (null != interfaces && interfaces.length > 0) {
            // jdk代理
        } else {
            return getProxyCglib(o);
        }
        return null;
    }

    public static Object getProxyCglib(Object o) throws Exception {
        Object proxied = o;
        for (Map.Entry<Class<? extends DynamicProxyCglibHandler>, List<Class<? extends Annotation>>> entry : map.entrySet()) {
            List<Class<? extends Annotation>> value = entry.getValue();

            Boolean proxyFlag = false;
            {
                Annotation[] annotations = o.getClass().getAnnotations();
                if (annotations != null && annotations.length > 0) {
                    for (Annotation annotation : annotations) {
                        if (value.stream().map(p -> p.getName()).collect(Collectors.toList()).contains(annotation.annotationType().getName())) {
                            proxied = setProxy(proxied, entry.getKey());
                            proxyFlag = true;
                            break;
                        }
                    }
                }
                if (proxyFlag) {
                    continue;
                }
            }


            {
                Method[] declaredMethods = o.getClass().getDeclaredMethods();
                if (declaredMethods != null && declaredMethods.length > 0) {
                    for (Method method : declaredMethods) {
                        if (!Modifier.isPublic(method.getModifiers())) {
                            continue;
                        }

                        Annotation[] annotations = method.getAnnotations();
                        if (annotations != null && annotations.length > 0) {
                            for (Annotation annotation : annotations) {

                                if (value.stream().map(p -> p.getName()).collect(Collectors.toList()).contains(annotation.annotationType().getName())) {
                                    proxied = setProxy(proxied, entry.getKey());
                                    proxyFlag = true;
                                    break;
                                }
                            }
                        }

                        if (proxyFlag) {
                            break;
                        }
                    }
                }
                if (proxyFlag) {
                    continue;
                }
            }

            /**
             * 通过注解校验是否需要代理
             */
  /*          for (Class<? extends Annotation> annotationClazz : entry.getValue()) {
                Annotation annotation = annotationClazz.newInstance();
                if (!(annotation instanceof Retention)) {
                    continue;
                }
                if (((Retention) annotation).value() != RetentionPolicy.RUNTIME) {
                    continue;
                }

                if (!(annotation instanceof Target)) {
                    continue;
                }

                Target annotationTarget = (Target) annotation;
                List<ElementType> elementTypes = Arrays.asList(annotationTarget.value());

                if (elementTypes.contains(ElementType.METHOD)) {
                    Method[] declaredMethods = o.getClass().getDeclaredMethods();
                    if (null == declaredMethods || declaredMethods.length <= 0) {
                        continue;
                    }

                    boolean flag1 = false;
                    for (Method method : declaredMethods) {
                        if (method.getAnnotation(annotationClazz) != null) {
                            flag1 = true;
                            break;
                        }
                    }

                    if (flag1) {
                        proxied = setProxy(proxied, entry.getKey());
                        continue;
                    }
                }

                if (elementTypes.contains(ElementType.TYPE)) {
                    if (annotationClazz.getAnnotation(annotationClazz) != null) {
                        proxied = setProxy(proxied, entry.getKey());
                        continue;
                    }
                }
            }*/
        }

        return proxied;
    }

    private static Object setProxy(Object proxied, Class<? extends DynamicProxyCglibHandler> clazz) throws Exception {
        /**
         * 创建代理
         */
        if (Enhancer.isEnhanced(proxied.getClass())) {
            /**
             * 存在代理，增加责任链
             */
            Field cglib$CALLBACK_0 = proxied.getClass().getDeclaredField("CGLIB$CALLBACK_0");
            cglib$CALLBACK_0.setAccessible(true);
            Object o = cglib$CALLBACK_0.get(proxied);

            Field advised = o.getClass().getField("handlers");
            advised.setAccessible(true);
            List list = (List) advised.get(o);
            list.add(clazz.newInstance());

            return proxied;

        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(proxied.getClass());
            List<DynamicProxyCglibHandler> objects = Lists.newArrayList();
            objects.add(clazz.newInstance());
            enhancer.setCallback(
                    DynamicProxyCglib.class.getConstructor(Object.class, List.class).newInstance(proxied, objects));
            return enhancer.create();
        }
    }
}