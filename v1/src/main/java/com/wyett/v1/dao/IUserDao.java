package com.wyett.v1.dao;

import com.wyett.v1.domain.User;

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
