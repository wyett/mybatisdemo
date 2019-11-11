package com.wyett.v5;

import com.wyett.v5.dao.IUserDao;
import com.wyett.v5.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/11 19:18
 * @description: TODO
 */

public class TestV5 {

    @Test
    public void testSelectAll() throws IOException {
        // read config file
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // get SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // get SqlSession
        SqlSession session = factory.openSession();
        // get userDao By proxy
        IUserDao userDao = session.getMapper(IUserDao.class);
        // print
        List<User> users = userDao.findAll();
        for(User user: users) {
            System.out.println(user);
        }

        // release
        session.close();
        in.close();
    }
}
