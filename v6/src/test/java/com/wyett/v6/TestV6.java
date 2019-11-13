package com.wyett.v6;

import com.wyett.v6.dao.IUserDao;
import com.wyett.v6.dao.impl.UserDaoImpl;
import com.wyett.v6.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/11 19:18
 * @description: TODO
 */

public class TestV6 {
    private InputStream in = null;
//    private SqlSession session = null;
    private IUserDao userDao = null;

    @Before
    public void init() throws IOException {
        // read config file
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // get SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // get SqlSession
//        session = factory.openSession();
        // get userDao By proxy
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void release() throws IOException {
//        session.commit();
//        session.close();
        in.close();
    }


    @Test
    public void testSelectAll() throws IOException {
        // print
        List<User> users = userDao.findAll();
        for(User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(BigInteger.valueOf(7));
        user.setUsername("Jim");
        user.setGender("男");
        user.setAddress("SH");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(BigInteger.valueOf(6));
        user.setUsername("Jim2");
        user.setGender("男");
        user.setAddress("SH");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDelete() {
        userDao.deleteOne(7);
    }

    @Test
    public void testSelectName() {
        System.out.println(userDao.selectName(3));
    }

    @Test
    public void testSelectSome() {
        List<User> users = userDao.selectSome("%J%");
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectTotal() {
        System.out.println(userDao.selectTotal());
    }

}
