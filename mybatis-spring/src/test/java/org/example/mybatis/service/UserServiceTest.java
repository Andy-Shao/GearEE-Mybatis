package org.example.mybatis.service;

import com.github.andyshao.mybatis.core.dto.Page;
import com.github.andyshao.mybatis.core.dto.Pageable;
import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/9
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@ExtendWith({SpringExtension.class})
@ContextConfiguration("classpath:spring.xml")
@Transactional
@Rollback
@EnabledIf(expression = "${integration.test}", loadContext = true)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testFindAllPage() {
        final Page<User> users = this.userService.findAllPage(Pageable.unPaged());
        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.getTotalElements()).isGreaterThan(0);
    }
}
