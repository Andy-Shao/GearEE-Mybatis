package org.example.mybatis.testing;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;

import java.io.IOException;
import java.util.List;

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

            final List<User> allUsers = mapper.findAll();
            Assertions.assertThat(allUsers).isNotNull();
            Assertions.assertThat(allUsers.size()).isGreaterThan(0);
        }
    }
}
