package com.example.lic.reflect.ioc;

import com.example.lic.reflect.ioc.annotations.WyAutoware;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * @author wy
 * @date 2020/6/17 17:33
 * @description
 */
public class RegisterHandler {

    public List<Class<?>> annotations;

    public RegisterHandler(List<Class<?>> annotations) {
        this.annotations = annotations;
    }

    /**
     * 注册bean
     *
     * @param result
     * @param clazzs
     * @author wy
     * @date 2020/6/17 17:36
     * @return:
     */
    public void register(Map<String, Object> result, List<Class<?>> clazzs) throws InstantiationException, IllegalAccessException {
        if (CollectionUtils.isEmpty(clazzs)) {
            return;
        }

        for (Class<?> clazz : clazzs) {
            register(result, clazz);
        }
    }

    /**
     * 注册单个
     *
     * @param result
     * @param clazzs
     * @author wy
     * @date 2020/6/17 17:38
     * @return:
     */
    private void register(Map<String, Object> result, Class<?> clazzs) throws IllegalAccessException, InstantiationException {

        /**
         * 判断注解
         */
        Annotation[] annotations1 = clazzs.getAnnotations();
        if (annotations1 == null) {
            return;
        }
        Boolean flag = false;
        for (Annotation annotation : annotations1) {
            for (Class<?> an : annotations) {
                if (an.isInstance(annotation)) {
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            return;
        }

        /**
         * 容器中是否已存在
         */
        if (result.containsKey(clazzs.getName())) {
            return;
        }

        /**
         * 构建代理
         * TODO 这里需要增加代理模式
         */
        Object o = clazzs.newInstance();

        /**
         * di注入
         * TODO 这里需要增加对循环依赖的判断
         */
        Field[] declaredFields = clazzs.getDeclaredFields();
        if (null != declaredFields) {
            for (Field field : declaredFields) {
                di(o, field, result);
            }
        }

        result.put(clazzs.getName(), o);
    }

    public void di(Object o, Field field, Map<String, Object> result) throws InstantiationException, IllegalAccessException {
        WyAutoware annotation = field.getAnnotation(WyAutoware.class);
        if (annotation != null) {
            String className = field.getType().getName();
            if (!result.containsKey(className)) {
                register(result, field.getType());
            }

            if (!result.containsKey(className)) {
                System.out.println("注入失败，元素不存在");
                return;
            }

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            field.set(o, result.get(className));
        }
    }
}