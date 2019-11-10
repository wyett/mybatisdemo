package com.wyett.v3.dao.impl;

import com.wyett.v3.dao.IUserDao;
import com.wyett.v3.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/9 18:25
 * @description: TODO
 */

public class IUserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public IUserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {

        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.wyett.v3.dao.IUserDao.findAll");
        session.close();
        return users;
    }
}
