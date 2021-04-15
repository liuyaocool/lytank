package com.liuyao.design_patterns;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class TestMybatis {

    public static void main(String[] args) {
        final InputStream is = TestMybatis.class.getClassLoader()
                .getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        final SqlSession sqlSession = sqlSessionFactory.openSession();

        final Object mapper = sqlSession.getMapper(Object.class);

    }
}
