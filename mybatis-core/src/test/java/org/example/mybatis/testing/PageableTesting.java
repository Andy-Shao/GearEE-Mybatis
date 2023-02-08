package org.example.mybatis.testing;

import com.github.andyshao.mybatis.core.dto.PageRequest;
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
 * Copyright: Copyright(c) 2023/2/8
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class PageableTesting {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.buildSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
            final UserMapping mapper = sqlSession.getMapper(UserMapping.class);

            final List<User> users = mapper.findByCreateUserPage("ShaoWeiChuang", PageRequest.of(1, 10));
            Assertions.assertThat(users).isNotNull();
        }
    }
}
