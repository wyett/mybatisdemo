package com.wyett.v5.dao;

import com.wyett.v5.domain.User;

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
}
