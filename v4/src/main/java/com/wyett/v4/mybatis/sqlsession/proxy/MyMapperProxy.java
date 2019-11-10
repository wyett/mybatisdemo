package com.wyett.v4.mybatis.sqlsession.proxy;

import com.wyett.v4.mybatis.cfg.Mapper;
import com.wyett.v4.mybatis.util.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 14:14
 * @description: TODO
 */

public class MyMapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MyMapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法名
        String methodName = method.getName();
        //获取方法对应的类名
        String className = method.getDeclaringClass().getName();
        //生成key
        String key = className + "." + methodName;
        // 根据key获取mapper
        Mapper mapper = mappers.get(key);
        // check
        if(mapper == null) {
            throw new IllegalArgumentException("no key: " + key);
        }
        return new Executor().selectList(mapper, conn);
    }
}
