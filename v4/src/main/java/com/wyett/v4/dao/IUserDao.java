package com.wyett.v4.dao;

import com.wyett.v4.domain.User;

import java.util.List;

/**
 * User dao interface
 */
public interface IUserDao {
    /**
     * @return
     */
    List<User> findAll();
}
