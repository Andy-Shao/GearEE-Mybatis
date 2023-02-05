package com.github.andyshao.mybatis.core.dto;

import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConditionalTest {
    @Test
    public void createConditionalTest() {
        final Conditional cond = new Conditional(User.class);
        cond.setDistinct(true);
        cond.createCriteria()
                .andEqualTo("ShaoWeiChuang", "create_user");
        cond.or()
                .andEqualTo("ShaoWeiChuang", "update_user");
        final List<Criteria> cs = cond.getOredCriteria();
        Assertions.assertThat(cs).isNotNull();
    }
}