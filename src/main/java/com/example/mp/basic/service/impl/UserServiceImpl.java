package com.example.mp.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mp.basic.entity.User;
import com.example.mp.basic.mapper.UserMapper;
import com.example.mp.basic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override public List<User> getAllUser() {
        return userMapper.getAll(new LambdaQueryWrapper<User>().ne(User::getName, 1));
    }

    @Override public List<User> getAllUser2() {
        return userMapper.getAll2(new LambdaQueryWrapper<User>().ne(User::getName, 2));
    }
}
