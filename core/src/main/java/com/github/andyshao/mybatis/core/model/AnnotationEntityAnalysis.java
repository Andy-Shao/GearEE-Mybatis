package com.github.andyshao.mybatis.core.model;

import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.reflect.FieldOperation;
import com.github.andyshao.util.ObjectOperation;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class AnnotationEntityAnalysis {
    public static Entity analysis(Class<?> entityClass) {
        final com.github.andyshao.mybatis.core.annotation.Entity annotation =
                entityClass.getAnnotation(com.github.andyshao.mybatis.core.annotation.Entity.class);
        return Entity.builder()
                .definition(entityClass)
                .name(ObjectOperation.functionNonNullOrDefault(
                        annotation,
                        it -> {
                            final String name = it.name();
                            if(StringOperation.isTrimEmptyOrNull(name)) return entityClass.getSimpleName();
                            return name;
                        },
                        entityClass.getSimpleName()))
                .properties(FieldOperation.superGetAllFieldForSet(entityClass)
                        .stream()
                        .map(it -> analysis(entityClass, it))
                        .collect(Collectors.toList()))
                .build();
    }

    private static Property analysis(Class<?> entityClass, Field field) {
        final Property.PropertyBuilder propertyBuilder = Property.builder();
        //TODO
        return propertyBuilder.build();
    }
}
