package com.example.mp.basic.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/basic/user")
public class UserController {

    @RequestMapping("/info")
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response){
        return "hello mybatis-plus";
    }
}

