package com.example.mp.basic.service;

import com.example.mp.basic.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
public interface IUserService extends IService<User> {

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 获取所有用户2
     *
     * @return
     */
    List<User> getAllUser2();
}
