package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.model.Entity;
import com.google.common.collect.Lists;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class ResultMappings {

    public static List<ResultMapping> buildDomainClassResultMapping(
            Class<?> resultType, Configuration configuration, Entity entity) {
        final ArrayList<ResultMapping> result = Lists.newArrayList();
        entity.getProperties()
                .forEach(property -> {
                    final ResultMapping resultMapping = new ResultMapping.Builder(configuration, property.getName())
                            .column(property.getColumn())
                            .javaType(property.getDefinition().getType())
                            .jdbcType(property.getJdbcType())
                            .build();
                    result.add(resultMapping);
                });
        return result;
    }

    public static boolean isDomainClass(Class<?> resultType) {
        final com.github.andyshao.mybatis.core.annotation.Entity annotation =
                resultType.getAnnotation(com.github.andyshao.mybatis.core.annotation.Entity.class);
        return Objects.nonNull(annotation);
    }
}
