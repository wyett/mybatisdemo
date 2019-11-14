package com.wyett.v9.dao;

import com.wyett.v9.domain.QueryVo;
import com.wyett.v9.domain.User;

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
    String selectName(Integer id);

    /**
     *
     * @param str
     * @return
     */
    List<User> selectSome(String str);

    /**
     *
     * @param user
     * @return
     */
    List<User> selectByCondition(User user);

    /**
     *
     * @param user
     * @return
     */
    List<User> selectByCondition1(User user);

    /**
     * query by QueryVo
     * @param qv
     * @return
     */
    List<User> selectByQueryVo(QueryVo qv);
}
