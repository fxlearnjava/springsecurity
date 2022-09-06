package com.springsecurity.mapper;

import com.springsecurity.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author fengxiang
* @description 针对表【u_users】的数据库操作Mapper
* @createDate 2022-09-03 19:38:23
* @Entity com.springsecurity.domain.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    Users selectUserByName(@Param("username") String username);
}




