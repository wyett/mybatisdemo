package com.wyett.v4.mybatis.io;

import java.io.InputStream;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 12:02
 * @description: 实现 Resources类
 */

public class Resources {
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
