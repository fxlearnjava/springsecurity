package com.springsecurity;

import com.springsecurity.domain.Users;
import com.springsecurity.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    UsersMapper userMapper;

    @Test
    void contextLoads() {
        Users user = userMapper.selectUserByName("zhangsan");
        System.out.println(user);
    }

}
