package com.pewa;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisFactory {
    private final static SqlSessionFactory factoryAdmin;
    private final static SqlSessionFactory factoryUser;

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config-admin.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factoryAdmin = new SqlSessionFactoryBuilder().build(reader);
    }

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config-user.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factoryUser = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory connectionAdmin() {
        return factoryAdmin;
    }

    public static SqlSessionFactory connectionUser() {
        return factoryUser;
    }
}
