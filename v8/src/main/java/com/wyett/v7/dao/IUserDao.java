package com.wyett.v7.dao;

import com.wyett.v7.domain.QueryVo;
import com.wyett.v7.domain.User;

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
     * input pojo
     * @param qv
     * @return
     */
    List<User> selectQueryVoUser(QueryVo qv);
}
