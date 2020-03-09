package com.example.mp.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mp.basic.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);

    List<User> getAll2(@Param(Constants.WRAPPER) Wrapper wrapper);
}
