package com.wyett.v11;

import com.wyett.v11.dao.IRoleDao;
import com.wyett.v11.dao.IUserDao;
import com.wyett.v11.domain.Role;
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

public class RoleTest {
    private InputStream in = null;
    private SqlSession session = null;
    private IRoleDao roleDao = null;

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
        roleDao = session.getMapper(IRoleDao.class);
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
        List<Role> roles =  roleDao.findAll();
        for(Role role: roles) {
            System.out.println("----------------------");
            System.out.println(role);
        }
    }
}
