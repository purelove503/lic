package com.example.lic.reflect.dynamic_proxy;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import com.example.lic.reflect.dynamic_proxy.annotations.Transaction;

/**
 * @author wy
 * @date 2020/6/17 16:28
 * @description
 */
public class RealObject implements Interface {

    @Log(type1 = "公共", type2 = "通用", type3 = "做某事", after = true)
    @Override
    public String doSomething() {
        System.out.println("实际对象输出 doSomething 方法");
        return "Success";
    }

    @Transaction
    @Override
    public void somethingElse(String msg) {
        System.out.println("实际对象输出 somethingElse 方法， msg: " + msg);
    }
}