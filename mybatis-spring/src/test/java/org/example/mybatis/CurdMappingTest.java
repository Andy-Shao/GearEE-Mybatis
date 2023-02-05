package org.example.mybatis;

import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.EnabledIf;
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
@ContextConfiguration("classpath:spring.xml")
@EnabledIf(expression = "${integration.test}", loadContext = true)
public class CurdMappingTest {
    @Autowired
    private UserMapping userMapping;

    @Test
    public void testFindByPrimaryKey() {
        final User user = this.userMapping.findByPrimaryKey("andy.shao");
        Assertions.assertThat(user).isNotNull();
    }
}
