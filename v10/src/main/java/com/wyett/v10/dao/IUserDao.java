package com.wyett.v10.dao;

import com.wyett.v10.domain.User;

import java.util.List;

/**
 *
 */
public interface IUserDao {
    /**
     * select all
     * @return
     */
    List<User> findAll();

    /**
     * select from user
     * @param id
     * @return
     */
    List<User> selectById(Integer id);
}
