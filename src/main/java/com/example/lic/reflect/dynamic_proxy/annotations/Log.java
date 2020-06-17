package com.example.lic.reflect.dynamic_proxy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解
 *
 * @author wy
 * @date 2019/11/22 13:53
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 一类
     */
    String type1() default "";

    /**
     * 二类
     */
    String type2() default "";

    /**
     * 三类
     */
    String type3() default "";

    /**
     * 过滤1
     */
    // TODO 不再使用过滤1，默认就是会员Id
     String filter1() default "";

    /**
     * 过滤2
     */
    String filter2() default "";

    /**
     * 是否打印入参
     */
    boolean before() default true;

    /**
     * 是否打印出参
     */
    boolean after() default false;
}