package org.example.mybatis.testing;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/5
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class CurdMappingTesting {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.buildSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
            final UserMapping mapper = sqlSession.getMapper(UserMapping.class);

            final User user = mapper.findByPrimaryKey("andy.shao");
            Assertions.assertNotNull(user);
        }
    }
}
