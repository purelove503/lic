package com.example.lic.reflect.ioc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动装配
 * TODO 后期需要增加按照类型或按照名称注入
 *
 * @author wy
 * @date 2020/6/17 17:41
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WyAutoware {
}
