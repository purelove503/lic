package com.example.lic.reflect.ioc.service;

import com.example.lic.reflect.dynamic_proxy.annotations.Log;
import com.example.lic.reflect.ioc.annotations.WyAutoware;
import com.example.lic.reflect.ioc.annotations.WyComponent;

/**
 * @author wy
 * @date 2020/6/17 18:05
 * @description
 */
@WyComponent
public class LoginService {

    @WyAutoware
    private UserService userService;

    @Log(type1 = "通用", type2 = "登录", after = true)
    public String login() {
        return "success";
    }
}