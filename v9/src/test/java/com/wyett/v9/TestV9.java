package com.wyett.v9;

import com.wyett.v9.dao.IUserDao;
import com.wyett.v9.domain.QueryVo;
import com.wyett.v9.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/11 19:18
 * @description: TODO
 */

public class TestV9 {
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
    public void testSelectByCondition() {
        User u = new User();
        u.setUsername("Mike");

        List<User> users = userDao.selectByCondition(u);
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByCondition1() {
        User u = new User();
        u.setUsername("Mike");
        u.setGender("女");

        List<User> users = userDao.selectByCondition(u);
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByQueryVo() {
        QueryVo qv = new QueryVo();
        qv.setIds(Arrays.asList(new Integer[]{1, 2, 3, 4, 5}));

        List<User> users = userDao.selectByQueryVo(qv);
        for(User user: users) {
            System.out.println(user);
        }
    }
}
