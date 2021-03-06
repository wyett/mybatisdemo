package com.wyett.v11;

import com.wyett.v11.dao.IUserDao;
import com.wyett.v11.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/11 19:18
 * @description: TODO
 */

public class TestV11 {
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
        session = factory.openSession(true);
        // get userDao By proxy
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void release() throws IOException {
//        session.commit();
        session.close();
        in.close();
    }


    @Test
    public void testSelectAll() throws IOException {
        // print
        List<User> users = userDao.findAll();
        for(User user: users) {
            System.out.println("----------------------");
            System.out.println(user);
        }
    }

    @Test
    public void testSelectById() {
        System.out.println(userDao.selectById(3));
    }

}
