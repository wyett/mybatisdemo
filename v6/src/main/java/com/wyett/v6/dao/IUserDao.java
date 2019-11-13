package com.wyett.v6.dao;

import com.wyett.v6.domain.User;

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
     * insert
     */
    void saveUser(User user);

    /**
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * delete from user
     * @param id
     */
    void deleteOne(Integer id);

    /**
     * select from user
     * @param id
     * @return
     */
    String selectName(Integer id);

    /**
     *
     * @param str
     * @return
     */
    List<User> selectSome(String str);

    /**
     * select count
     * @return
     */
    int selectTotal();

}
