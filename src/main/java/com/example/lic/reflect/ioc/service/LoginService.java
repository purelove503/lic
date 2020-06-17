package com.example.lic.reflect.ioc.service;

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
}