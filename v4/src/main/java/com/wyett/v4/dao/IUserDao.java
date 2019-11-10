package com.wyett.v4.dao;

import com.wyett.v4.domain.User;
import com.wyett.v4.mybatis.annotations.Select;

import java.util.List;

/**
 * User dao interface
 */
public interface IUserDao {
    /**
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
