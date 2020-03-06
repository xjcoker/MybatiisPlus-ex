package com.example.mp.basic.service.impl;

import com.example.mp.basic.entity.User;
import com.example.mp.basic.mapper.UserMapper;
import com.example.mp.basic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
