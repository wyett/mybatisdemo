package com.wyett.v3.dao;

import com.wyett.v3.domain.User;

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
