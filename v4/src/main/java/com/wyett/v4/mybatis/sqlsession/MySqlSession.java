package com.wyett.v4.mybatis.sqlsession;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 12:26
 * @description: TODO
 */

public interface MySqlSession {
    /**
     *
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
