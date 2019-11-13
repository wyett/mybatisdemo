package com.wyett.v7;

import com.wyett.v7.dao.IUserDao;
import com.wyett.v7.domain.QueryVo;
import com.wyett.v7.domain.User;
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

public class TestV7{
    private InputStream in = null;
    private SqlSession session = null;
    private IUserDao userDao = null;

    @Before
    public void init() throws IOException {
        // read config file
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // get SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // get SqlSession
        session = factory.openSession();
        // get userDao By proxy
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void release() throws IOException {
        session.commit();
        session.close();
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
        user.setId(BigInteger.valueOf(10));
        user.setUsername("Jim3");
        user.setGender("男");
        user.setAddress("SH");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(BigInteger.valueOf(7));
        user.setUsername("Jim1");
        user.setGender("男");
        user.setAddress("SH");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDelete() {
        userDao.deleteOne(5);
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
    public void testSelectQueryVoUser() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%J%");
        queryVo.setUser(user);

        List<User> users = userDao.selectQueryVoUser(queryVo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
