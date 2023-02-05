package org.example.mybatis;

import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/5
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@ExtendWith({SpringExtension.class})
@ContextConfiguration("classpath:spring.xml")
@EnabledIf(expression = "${integration.test}", loadContext = true)
public class CoreMappingTest {
    @Autowired
    private UserMapping userMapping;

    @Test
    public void testFindAll(){
        final List<User> users = userMapping.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertNotEquals(users.size(), 0);
    }
}
