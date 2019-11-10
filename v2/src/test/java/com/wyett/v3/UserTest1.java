package com.wyett.v3;

import com.wyett.v3.dao.IUserDao;
import com.wyett.v3.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        // 使用SqlSession实现dao的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 使用代理对象调用方法
        List<User> users = userDao.findAll();
        for(User user: users) {
            System.out.println(user);
        }
        // 释放资源
        session.close();
        in.close();
    }
}
