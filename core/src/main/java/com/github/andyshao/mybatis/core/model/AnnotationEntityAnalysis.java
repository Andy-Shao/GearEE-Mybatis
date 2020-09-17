package com.github.andyshao.mybatis.core.model;

import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.mybatis.core.MybatisException;
import com.github.andyshao.mybatis.core.annotation.Column;
import com.github.andyshao.mybatis.core.annotation.Id;
import com.github.andyshao.reflect.FieldOperation;
import com.github.andyshao.util.ObjectOperation;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
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
        final List<Property> properties = FieldOperation.superGetAllFieldForSet(entityClass)
                .stream()
                .map(it -> analysis(entityClass, it))
                .collect(Collectors.toList());
        final List<Property> pk = properties.stream()
                .filter(Property::getIsPrimaryKey)
                .collect(Collectors.toList());
        if(pk.size() > 1) throw new MybatisException("the quantity of entity primary key greater than one");
        return Entity.builder()
                .definition(entityClass)
                .name(ObjectOperation.functionNonNullOrDefault(
                        annotation,
                        it -> {
                            final String name = it.tableName();
                            if(StringOperation.isTrimEmptyOrNull(name)) return entityClass.getSimpleName();
                            return name;
                        },
                        entityClass.getSimpleName()))
                .properties(properties)
                .primaryKey(pk.isEmpty() ? null : pk.get(0))
                .build();
    }

    private static Property analysis(Class<?> entityClass, Field field) {
        final Property.PropertyBuilder propertyBuilder = Property.builder();
        final Id id = field.getAnnotation(Id.class);
        final Column column = field.getAnnotation(Column.class);
        return propertyBuilder
                .definition(field)
                .isPrimaryKey(Objects.nonNull(id))
                .column(getColumnName(field, column))
                .jdbcType(getJdbcType(column))
                .name(field.getName())
                .build();
    }

    public static JdbcType getJdbcType(Column column) {
        return ObjectOperation.functionNonNullOrDefault(
                column,
                Column::jdbcType,
                JdbcType.VARCHAR);
    }

    public static final String getColumnName(Field field, Column column) {
        return ObjectOperation.functionNonNullOrDefault(
                column,
                it -> {
                    final String columnName = it.value();
                    if(StringOperation.isTrimEmptyOrNull(columnName)) return field.getName();
                    return columnName;
                },
                field.getName());
    }
}
