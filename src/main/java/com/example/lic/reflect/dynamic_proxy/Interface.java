package com.example.lic.reflect.dynamic_proxy;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import com.example.lic.reflect.dynamic_proxy.annotations.Transaction;

/**
 * @author wy
 * @date 2020/6/17 16:27
 * @description 动态代理接口
 */
public interface Interface {

    String doSomething();

    void somethingElse(String msg);
}
