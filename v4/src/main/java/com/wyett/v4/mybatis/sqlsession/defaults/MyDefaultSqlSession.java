package com.wyett.v4.mybatis.sqlsession.defaults;

import com.wyett.v4.mybatis.cfg.Configuration;
import com.wyett.v4.mybatis.sqlsession.MySqlSession;
import com.wyett.v4.mybatis.sqlsession.proxy.MyMapperProxy;
import com.wyett.v4.mybatis.util.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 14:02
 * @description: TODO
 */

public class MyDefaultSqlSession implements MySqlSession {
    private Configuration cfg;
    private Connection conn;

    public MyDefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        conn = DataSourceUtil.getConnection(cfg);
    }

    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MyMapperProxy(cfg.getMappers(), conn));
    }

    public void close() {
        if(conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
