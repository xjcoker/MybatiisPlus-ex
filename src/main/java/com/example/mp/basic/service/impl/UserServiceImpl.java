package com.example.mp.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override public IPage<User> getUserPage(Page<?> page) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
         page.setOptimizeCountSql(true);
         page.setSearchCount(true);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return userMapper.selectPageVo(page);
    }
}
