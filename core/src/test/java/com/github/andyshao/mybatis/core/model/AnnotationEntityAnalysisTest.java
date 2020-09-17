package com.github.andyshao.mybatis.core.model;

import org.assertj.core.api.Assertions;
import org.example.mybatis.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationEntityAnalysisTest {

    @Test
    void analysis() {
        final Entity entity = AnnotationEntityAnalysis.analysis(User.class);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getName()).isEqualTo("user");
    }
}