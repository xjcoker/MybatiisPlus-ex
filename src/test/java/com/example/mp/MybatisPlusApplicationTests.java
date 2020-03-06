package com.example.mp;

import com.example.mp.basic.entity.User;
import com.example.mp.basic.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisPlusApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> demoUserList = userMapper.selectList(null);
		Assert.assertEquals(5, demoUserList.size());
		demoUserList.forEach(System.out::println);
	}
}
