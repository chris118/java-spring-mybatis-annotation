package com.hhit.springmvc.db;

import com.hhit.springmvc.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select(value = "select * from user where id=#{id}")
    User getUser(Long id);
}
