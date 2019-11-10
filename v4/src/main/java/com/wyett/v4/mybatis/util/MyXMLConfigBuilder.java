package com.wyett.v4.mybatis.util;

import com.wyett.v4.mybatis.cfg.Configuration;
import com.wyett.v4.mybatis.cfg.Mapper;
import com.wyett.v4.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : wyettLei
 * @date : Created in 2019/11/10 12:37
 * @description: TODO
 */

public class MyXMLConfigBuilder {
    public static Configuration loadConfiguration(InputStream config) {
        Configuration cfg = new Configuration();
        try {
//            Configuration cfg = new Configuration();
            SAXReader reader = new SAXReader();
            Document document = reader.read(config);
            Element root = document.getRootElement();
            List<Element> propertyElements = root.selectNodes("//property");
            for (Element propertyElement : propertyElements) {
                String name = propertyElement.attributeValue("name");
                if ("driver".equals(name)) {
                    String driver = propertyElement.attributeValue("value");
                    cfg.setDriver(driver);
                }
                if ("url".equals(name)) {
                    String driver = propertyElement.attributeValue("value");
                    cfg.setUrl(driver);
                }
                if ("username".equals(name)) {
                    String driver = propertyElement.attributeValue("value");
                    cfg.setUsername(driver);
                }
                if ("password".equals(name)) {
                    String driver = propertyElement.attributeValue("value");
                    cfg.setPassword(driver);
                }
            }
            List<Element> mapperElements = root.selectNodes("//mappers//mapper");
            for (Element mapperElement : mapperElements) {
                Attribute attribute = mapperElement.attribute("resource");
                if (attribute != null) {
                    System.out.println("use XML");
                    String mapperPath = attribute.getValue();
                    Map<String, Mapper> mappers = loadMapperConfiguration(mapperPath);
                    cfg.setMappers(mappers);
                } else {
//                    System.out.println("use Annotation");
//                    String daoClassPath = mapperElement.attributeValue("class");
//                    Map<String, Mapper> mappers = loadMapperAnnotation(daoClassPath);
//                    cfg.setMappers(mappers);
                }
            }

//            return cfg;

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return cfg;
    }


    private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) {
        InputStream in = null;
        Map<String, Mapper> mappers = new HashMap<String, Mapper>();
        try {
//            Map<String, Mapper> mappers = new HashMap<String, Mapper>();
            in = Resources.getResourceAsStream(mapperPath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            String namespace = root.attributeValue("namespace");
            List<Element> selectElements = root.selectNodes("select");
            for(Element selectElement : selectElements) {
                String id = selectElement.attributeValue("id");
                String resultType = selectElement.attributeValue("resultType");
                String queryString = selectElement.getText();
                String key = namespace + "." + id;
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultString(resultType);
                mappers.put(key, mapper);
            }
//            return mappers;

        }catch (Exception e) {

        }
        return mappers;
    }
}
