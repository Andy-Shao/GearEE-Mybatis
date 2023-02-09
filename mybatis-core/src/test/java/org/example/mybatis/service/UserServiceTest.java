package org.example.mybatis.service;

import com.github.andyshao.mybatis.core.dto.Page;
import com.github.andyshao.mybatis.core.dto.PageRequest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.example.mybatis.testing.Utils;

import java.io.IOException;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/9
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class UserServiceTest {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.buildSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
            final UserMapping mapper = sqlSession.getMapper(UserMapping.class);
            final UserService userService = new UserService(mapper);
            final Page<User> users = userService.findByCreateUserPage("ShaoWeiChuang", PageRequest.of(1, 10));
            Assertions.assertThat(users).isNotNull();
            Assertions.assertThat(users.getTotalElements()).isGreaterThan(0);
        }
    }
}
