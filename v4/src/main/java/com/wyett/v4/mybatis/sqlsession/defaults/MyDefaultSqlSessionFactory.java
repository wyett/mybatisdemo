package com.wyett.v4.mybatis.sqlsession.defaults;

import com.wyett.v4.mybatis.cfg.Configuration;
import com.wyett.v4.mybatis.sqlsession.MySqlSession;
import com.wyett.v4.mybatis.sqlsession.MySqlSessionFactory;

import java.sql.Connection;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 13:55
 * @description: TODO
 */

public class MyDefaultSqlSessionFactory implements MySqlSessionFactory {
    private Configuration cfg;

    public MyDefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;

    }

    @Override
    public MySqlSession openSession() {
        return new MyDefaultSqlSession(cfg);
    }
}
