package com.pewa.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by phonik on 2017-04-03.
 */
public abstract class NewDataBase extends MyBatisFactory {
    private final static SqlSessionFactory factoryOther;

    private final static Logger log = LogManager.getLogger(NewDataBase.class);

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config-user.xml");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        factoryOther = new SqlSessionFactoryBuilder().build(reader);
    }
    public static SqlSessionFactory nowaBaza() {
        return factoryOther;
    }
}
