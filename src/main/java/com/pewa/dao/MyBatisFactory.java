package com.pewa.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public abstract class MyBatisFactory implements SqlSession {

    private final static SqlSessionFactory factoryUser;

    private static final Logger log = LogManager.getLogger(MyBatisFactory.class);

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config-user.xml");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        // second builder argument refers to enviroment id within mybatis xml config
        factoryUser = new SqlSessionFactoryBuilder().build(reader, "development");
    }

    public static SqlSessionFactory connectionUser() {
        return factoryUser;
    }
}
