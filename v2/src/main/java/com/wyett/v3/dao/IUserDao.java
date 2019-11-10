package com.wyett.v3.dao;

import com.wyett.v3.domain.User;
import org.apache.ibatis.annotations.Select;

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
