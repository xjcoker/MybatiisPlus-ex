package com.example.mp.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mp.basic.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
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

    /**
     * 获取分页用户
     *
     * @param page
     * @return
     */
    IPage<User> getUserPage(Page<?> page);
}
