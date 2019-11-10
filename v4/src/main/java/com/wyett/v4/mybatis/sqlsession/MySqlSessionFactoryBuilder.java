package com.wyett.v4.mybatis.sqlsession;

import com.wyett.v4.mybatis.cfg.Configuration;
import com.wyett.v4.mybatis.io.Resources;
import com.wyett.v4.mybatis.sqlsession.defaults.MyDefaultSqlSessionFactory;
import com.wyett.v4.mybatis.util.MyXMLConfigBuilder;

import java.io.InputStream;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 12:20
 * @description: TODO
 */

public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory build(InputStream in) {
        Configuration cfg = MyXMLConfigBuilder.loadConfiguration(in);
        return new MyDefaultSqlSessionFactory(cfg);
    }

}
