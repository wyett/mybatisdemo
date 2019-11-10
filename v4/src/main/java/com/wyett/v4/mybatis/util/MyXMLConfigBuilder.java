package com.wyett.v4.mybatis.util;

import com.wyett.v4.mybatis.annotations.Select;
import com.wyett.v4.mybatis.cfg.Configuration;
import com.wyett.v4.mybatis.cfg.Mapper;
import com.wyett.v4.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
//        Configuration cfg = new Configuration();
        try {
            Configuration cfg = new Configuration();
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
                    System.out.println("use Annotation");
                    String daoClassPath = mapperElement.attributeValue("class");
                    Map<String, Mapper> mappers = loadMapperAnnotation(daoClassPath);
                    cfg.setMappers(mappers);
                }
            }
            return cfg;
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                config.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

        }catch (Exception e) {

        }
        return mappers;
    }

    private static Map<String, Mapper>
    loadMapperAnnotation(String daoClassPath) throws ClassNotFoundException {
        // define return value
        Map<String, Mapper> mappers = new HashMap<String, Mapper>();
        // get dao
        Class daoClass = Class.forName(daoClassPath);
        // get function array
        Method[] methods = daoClass.getMethods();
        // scan methods
        for(Method method: methods) {
            // check if method has Select annotation
            boolean isAnnotation = method.isAnnotationPresent(Select.class);
            if(isAnnotation){
                Mapper mapper = new Mapper();
                // get value of Select annotation
                Select selectAnnotation = method.getAnnotation(Select.class);
                String queryString = selectAnnotation.value();
                mapper.setQueryString(queryString);
                // get return of current func with Generic info
                Type type = method.getGenericReturnType();
                // check if type is parameterized type
                if(type instanceof ParameterizedType) {
                    // cast
                    ParameterizedType parameterizedType = (ParameterizedType)type;
                    // get real parameter type in parameterized type
                    Type[] types = parameterizedType.getActualTypeArguments();
                    // get first class
                    Class domainClass = (Class)types[0];
                    // get class name of domainClass
                    String resultString = domainClass.getName();
                    //
                    mapper.setResultString(resultString);
                }
                // get method name
                String methodName = method.getName();
                // get class name
                String className = method.getDeclaringClass().getName();
                // get key
                String key = className + "." + methodName;
                // set mappers
                mappers.put(key, mapper);
            }
        }
        return mappers;
    }

}
