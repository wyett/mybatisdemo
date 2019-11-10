package com.wyett.v4;

import com.wyett.v4.dao.IUserDao;
import com.wyett.v4.domain.User;
import com.wyett.v4.mybatis.io.Resources;
import com.wyett.v4.mybatis.sqlsession.MySqlSession;
import com.wyett.v4.mybatis.sqlsession.MySqlSessionFactory;
import com.wyett.v4.mybatis.sqlsession.MySqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/9 17:21
 * @description: TODO
 */

public class UserTest1 {
    public static void main(String[] args) throws IOException {
        // 读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory工厂
        MySqlSessionFactoryBuilder builder = new MySqlSessionFactoryBuilder();
        MySqlSessionFactory factory = builder.build(in);
        // 使用工厂创建SqlSession对象
        MySqlSession session = factory.openSession();
        // 使用SqlSession实现dao的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 使用代理对象调用方法
        List<User> users = userDao.findAll();
        for(User user: users) {
            System.out.println(user);
        }
        // 关闭接口
        session.close();
        in.close();
    }
}
