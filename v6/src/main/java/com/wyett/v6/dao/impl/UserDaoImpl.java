package com.wyett.v6.dao.impl;

import com.wyett.v6.dao.IUserDao;
import com.wyett.v6.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/12 18:55
 * @description: TODO
 */

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.wyett.v6.dao.IUserDao.findAll");
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.wyett.v6.dao.IUserDao.saveUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("com.wyett.v6.dao.IUserDao.updateUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void deleteOne(Integer id) {
        SqlSession session = factory.openSession();
        session.update("com.wyett.v6.dao.IUserDao.deleteOne", id);
        session.commit();
        session.close();
    }

    @Override
    public String selectName(Integer id) {
        SqlSession session = factory.openSession();
        String name = session.selectOne("com.wyett.v6.dao.IUserDao.selectName",
                id);
        session.close();
        return name;
    }

    @Override
    public List<User> selectSome(String str) {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.wyett.v6.dao.IUserDao.selectSome",
                str);
        session.close();
        return users;
    }

    @Override
    public int selectTotal() {
        SqlSession session = factory.openSession();
        int count = session.selectOne("com.wyett.v6.dao.IUserDao.selectTotal");
        session.close();
        return count;
    }
}
