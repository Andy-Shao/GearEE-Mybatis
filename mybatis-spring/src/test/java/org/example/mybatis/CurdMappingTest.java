package org.example.mybatis;

import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.example.mybatis.mapping.UserMapping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
@Transactional
@Rollback(value = true)
public class CurdMappingTest {
    @Autowired
    private UserMapping userMapping;

    @Test
    public void testFindByPrimaryKey() {
        final User user = this.userMapping.findByPrimaryKey("andy.shao");
        Assertions.assertThat(user).isNotNull();
    }

    @Test
    public void testSaveItem(){
        final User user = new User();
        user.setUsername("Weichuang Shao");
        user.setPassword("13035959");
        user.setCreateUser("andy.shao");
        user.setCreateTime(LocalDateTime.now());
        this.userMapping.saveSelective(user);
    }

    @Test
    public void testDeleteItem() {
        this.testSaveItem();
        this.userMapping.deleteByPrimaryKey("Weichuang Shao");
    }

    @Test
    public void testUpdateItem() {
        this.testSaveItem();
        final User user = this.userMapping.findByPrimaryKey("Weichuang Shao");
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser("andy.shao");
        this.userMapping.updateByPrimaryKeySelective(user);
    }
}
