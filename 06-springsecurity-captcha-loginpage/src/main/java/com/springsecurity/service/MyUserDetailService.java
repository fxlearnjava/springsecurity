package com.springsecurity.service;


import com.springsecurity.domain.Users;
import com.springsecurity.mapper.UsersMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    UsersMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //进入数据库查询用户对象
        Users users = userMapper.selectUserByName(username);
        //判断是否存在该用户
        if (users != null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //将user对象转换为UserDetails
        UserDetails userDetails = User.withUsername(users.getUsername())
                .password(users.getPassword())   //数据库中密码是加密的，不需要再进行加密
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .build();

        return userDetails;
    }
}
