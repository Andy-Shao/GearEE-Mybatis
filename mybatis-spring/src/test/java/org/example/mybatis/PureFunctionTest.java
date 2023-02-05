package org.example.mybatis;

import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/5
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = "classpath:spring.xml")
public class PureFunctionTest {
    @Autowired
    private UserMapping userMapping;

    @Test
    public void testSpring() {}

    @Test
    public void testBasicFunction(){
        final User user = this.userMapping.findByUsername("andy.shao");
        Assertions.assertNotNull(user);
        System.out.println(user);
    }
}
