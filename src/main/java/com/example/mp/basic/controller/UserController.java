package com.example.mp.basic.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mp.basic.entity.User;
import com.example.mp.basic.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiangjun.song
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/basic/user") public class UserController {
    @Autowired private IUserService userService;

    @RequestMapping("/info-page")
    public IPage<User> getUserInfoPage(@RequestBody Page page,HttpServletRequest request, HttpServletResponse response) {
        return userService.getUserPage(page);
    }

    @RequestMapping("/info")
    public List<User> getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        //eq等于 （链式查询）
        userService.lambdaQuery().eq(User::getAge, 25).list();
        userService.lambdaQuery().eq(User::getName, "value").eq(User::getAge, "value").list();
        userService.lambdaQuery().eq(1 == 1, User::getName, "value").list();
        //allEq字段条件 泛型组合 此处用问号以模糊map key
        Map<SFunction<User, ?>, Object> paramMap = new HashMap<>(4);
        paramMap.put(User::getId, 1);
        paramMap.put(User::getName, "mp");
        paramMap.put(User::getAge, null);
        //allEq全部等于map条件 参数为null时可设置是否忽略null字段数据
        userService.list(new LambdaQueryWrapper<>());
        userService.lambdaQuery().allEq(paramMap);
        userService.lambdaQuery().allEq(paramMap, false).list();
        //对待查询的字段名进行过滤
        //        userService.lambdaQuery().allEq((k, v) -> k.indexOf("a") > 0, paramMap, false).list();
        //第一个参数为是否执行后面的查询条件
        userService.lambdaQuery().allEq(true, paramMap, false).list();
        //不等于
        userService.lambdaQuery().ne(User::getAge, 10).list();
        //大于gt ge大于等于
        userService.lambdaQuery().gt(User::getAge, 18).list();
        //小于 le小于等于
        userService.lambdaQuery().lt(User::getAge, 5).list();
        //区间内
        userService.lambdaQuery().between(User::getAge, 10, 18).list();
        //区间外
        userService.lambdaQuery().notBetween(User::getAge, 10, 18).list();
        //like匹配 两边添加%
        userService.lambdaQuery().like(1 == 1, User::getName, "王").list();
        //notLike匹配 两边添加%
        userService.lambdaQuery().notLike(1 == 1, User::getName, "王").list();
        //like匹配 左边添加%ikeRight右边添加%
        userService.lambdaQuery().likeLeft(1 == 1, User::getName, "王").list();
        //字段为空 isNotNull同
        userService.lambdaQuery().isNotNull(1 == 1, User::getAge).list();
        //in 值集合检索 notIn同
        userService.lambdaQuery().in(1 == 1, User::getAge, Arrays.asList(1, 2)).list();
        //in sql写法 notIn同
        userService.lambdaQuery().inSql(1 == 1, User::getAge, "1,2,3,4,5,6").list();
        //分组
        userService.lambdaQuery().groupBy(1 == 1, User::getName).count();
        //降序排序
        userService.lambdaQuery().orderByDesc(1 == 1, User::getAge).list();
        //升序排序
        userService.lambdaQuery().orderByAsc(1 == 1, User::getId).list();
        //排序组合
        userService.lambdaQuery().orderBy(1 == 1, "isAsc".equals("isAsc"), User::getId).list();
        //having过滤
        userService.lambdaQuery().having("sum(age) > {0}", "11").list();
        userService.lambdaQuery().having(1 == 1, "sum(age) > {0}", "11").list();
        userService.lambdaQuery().having("sum(age) > 11").list();
        //or
        userService.lambdaQuery().eq(User::getId, 1).or().eq(User::getName, "老")
            .or(i -> i.eq(User::getName, "李白").ne(User::getEmail, "xx.com")).or(true).list();
        //and
        userService.lambdaQuery().and(i -> i.eq(User::getName, "李白").ne(User::getEmail, "xx.com")).list();
        //nested
        userService.lambdaQuery().nested(1 == 1, i -> i.eq(User::getName, "李").ne(User::getEmail, "xx")).list();
        //apply 手动sql匹配
        userService.lambdaQuery().apply(1 == 1, "date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08");
        //last 直接拼接到sql最后 多次调用以最后一次为主 有sql注入风险
        userService.lambdaQuery().last(1 == 1, "limit 1");
        //exists notExists同
        userService.lambdaQuery().exists(1 == 1, "select id from user where age = 1");
        //自身的内部属性 entity 也用于生成 where 条件
        userService.lambdaQuery()
            .select(User::getId, User::getAge, User::getEmail, User::getName, i -> i.getEmail().startsWith("test"));
        //        userService.getAllUser();
        return userService.getAllUser2();
    }

    @RequestMapping("/add")
    public String addUser(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setId(1L);
        user.setAge(12);
        user.setEmail("sxj.email");
        user.setName("小明");
        userService.save(user);
        userService.saveBatch(new ArrayList() {{
            add(user);
        }});
        userService.saveBatch(new ArrayList() {{
            add(user);
        }}, 1);
        userService.saveOrUpdate(user);
        userService.saveOrUpdate(user, new Wrapper<User>() {
            @Override public User getEntity() {
                return null;
            }

            @Override public MergeSegments getExpression() {
                return null;
            }

            @Override public void clear() {

            }

            @Override public String getSqlSegment() {
                return null;
            }
        });
        userService.saveOrUpdateBatch(new ArrayList() {{
            add(user);
        }});
        userService.saveOrUpdateBatch(new ArrayList() {{
            add(user);
        }}, 1);
        userService.lambdaUpdate().set(1 == 1, User::getName, "老李头");
        new UpdateWrapper<>().lambda().setSql("name = '老李头'");

        return "hello mybatis-plus";
    }
}

