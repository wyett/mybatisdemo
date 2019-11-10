package com.wyett.v4.mybatis.util;

import com.wyett.v4.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 15:28
 * @description: TODO
 */

public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
//        List<E> list = new ArrayList<E>();
        try {
            //解析mapper
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultString();
            //定义链接驱动
            Class domainClass = Class.forName(resultType);
            //获取prepareStatement
            ps = conn.prepareStatement(queryString);
            //执行
            rs = ps.executeQuery();
            //封装结果
            List<E> list = new ArrayList<E>();
            while (rs.next()) {
                //创建链接对象
                E obj = (E)domainClass.newInstance();
                //获取结果集元信息
                ResultSetMetaData rsmd = rs.getMetaData();
                //获取column数量
                int columnCount = rsmd.getColumnCount();
                //遍历
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名
                    String columnName = rsmd.getColumnName(i);
                    //根据列名获取值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值,使用内省机制
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,
                            domainClass);
                    //获取写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列值赋值给对象
                    writeMethod.invoke(obj, columnValue);
                }
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
//            e.printStackTrace();
        } finally {
            release(ps, rs);
        }
//        return list;
    }

    private void release(PreparedStatement ps, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
