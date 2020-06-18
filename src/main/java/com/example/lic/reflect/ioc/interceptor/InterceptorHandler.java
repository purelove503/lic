package com.example.lic.reflect.ioc.interceptor;

import lombok.Data;

/**
 * @author wy
 * @date 2020/6/17 18:33
 * @description
 */
@Data
public abstract class InterceptorHandler {

    public Object proxied;
    public InterceptorHandler(Object proxied) {
        this.proxied = proxied;
    }

    public abstract Boolean canInterceptor(Class<?> clazz);

    public final Object getProxiedOriginal() {
        if (this.proxied instanceof InterceptorHandler) {
            return ((InterceptorHandler) this.proxied).getProxied();
        } else {
            return proxied;
        }
    }

    public final Object getProxied() {
        return this.proxied;
    }
}
