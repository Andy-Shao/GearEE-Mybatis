package org.example.mybatis.testing;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.Assertions;
import org.example.mybatis.mapping.UserMapping;

import java.io.IOException;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/18
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class CoreMappingTesting {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.buildSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
            final UserMapping mapper = sqlSession.getMapper(UserMapping.class);

            final int userNumber = mapper.countAll();
            Assertions.assertThat(userNumber).isGreaterThan(0);
        }
    }
}
