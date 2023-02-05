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
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class BasicMappingTesting {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.buildSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
            final UserMapping mapper = sqlSession.getMapper(UserMapping.class);
            final String username = "andy.shao";
            final User user = mapper.findByUsername(username);
            Assertions.assertThat(user).isNotNull();
            Assertions.assertThat(user.getUsername()).isEqualTo(username);
            Assertions.assertThat(user.getPassword()).isEqualTo("1303595");
            Assertions.assertThat(user.getCreateUser()).isEqualTo("ShaoWeiChuang");

            final List<User> allUsers = mapper.findAllUsers();
            Assertions.assertThat(allUsers).isNotNull();

            final int userNumber = mapper.userNumber();
            Assertions.assertThat(userNumber).isGreaterThan(0);
        }
    }
}
