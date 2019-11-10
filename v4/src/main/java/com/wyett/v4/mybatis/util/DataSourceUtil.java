package com.wyett.v4.mybatis.util;

import com.wyett.v4.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 15:57
 * @description: TODO
 */

public class DataSourceUtil {
    public static Connection getConnection(Configuration cfg){
        Connection conn = null;
        try{
            Class.forName(cfg.getDriver());
            conn = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(),
                    cfg.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
