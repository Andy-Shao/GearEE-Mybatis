package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.mybatis.core.annotation.Column;
import com.github.andyshao.mybatis.core.annotation.Entity;
import com.github.andyshao.mybatis.core.mapping.CoreMapping;
import com.github.andyshao.mybatis.core.mapping.CurdMapping;
import com.github.andyshao.mybatis.core.model.AnnotationEntityAnalysis;
import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshao.reflect.FieldOperation;
import com.github.andyshao.util.ObjectOperation;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 20, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class Mappers {
	private Mappers() {}
	public static final String GENERIC_DAO_QUERY = "GENERIC_DAO_QUERY";
    @Deprecated
    public static final String CONDITIONAL_DAO_QUERY = "CONDITIONAL_DAO_QUERY";
	public static final String DEFAULT_PK_NAME = "pk";
	public static final String DEFAULT_PK_LIST_NAME = "pkLs";
	public static final String DEFAULT_ENTITY_NAME = "entity";
	public static final String DEFAULT_ENTITY_LIST_NAME = "entityLs";
    @Deprecated
	public static final String DEFAULT_CONDITIONAL_NAME = "cond";
	public static final String DEFAULT_SETS_NAME = "sets";
    @Deprecated
	public static final String DEFAULT_SORT_NAME = "sort";
	public static final String DEFAULT_PAGE_NAME = "pageable";
	@SuppressWarnings("rawtypes")
	public static final Class[] GENERIC_DAO_CLASS = new Class[] {
            CoreMapping.class, CurdMapping.class
    };

    public static boolean isProcessing(String query){
        if(Objects.equals(GENERIC_DAO_QUERY, query)) return true;
        return false;
    }
	
	public static final String getColumnName(@NonNull Field field) {
		Column column = field.getAnnotation(Column.class);
        return AnnotationEntityAnalysis.getColumnName(field, column);
    }

    public static final Map<String, String> getColumnNames(Class<?> domainClass) {
		Map<String, String> ret = new HashMap<String, String>();
		FieldOperation.superGetAllFieldForSet(domainClass)
			.stream()
			.filter(field -> !Modifier.isStatic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers()))
			.forEach(field -> ret.put(field.getName(), getColumnName(field)));
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	public static final Class[] getGenericType(@NonNull Class<?> customMapper) {
        Type[] types = customMapper.getGenericInterfaces();
        Class[] result = new Class[2];
        for(Type type : types) {
            BASE_DAO:
            for(Class<?> baseDao : GENERIC_DAO_CLASS) {
                if(type.getTypeName().startsWith(baseDao.getName())) {
                    if(type instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) type;
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

                        Class<?> entity = null;
                        try {
                            entity = ClassOperation.forName(actualTypeArguments[0].getTypeName());
                        } catch (com.github.andyshao.reflect.ClassNotFoundException e) {
                            entity = null;
                        }
                        if(entity != null) {
                            result[0] = entity;
                        }

                        if(actualTypeArguments.length > 1) {
                            Class<?> pk = null;
                            try {
                                pk = ClassOperation.forName(actualTypeArguments[1].getTypeName());
                            } catch (com.github.andyshao.reflect.ClassNotFoundException e) {
                                continue BASE_DAO;
                            }
                            if(pk != null) {
                                result[1] = pk;
                            }
                        }
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
            }
            return result;
        }

        throw new UnsupportedOperationException();
    }

    public static final String getEntityName(Class<?> daoClass) {
        final Class<?> entityType = getEntityType(daoClass);
        Entity entity = entityType.getAnnotation(Entity.class);
        return ObjectOperation.functionNonNullOrElseGet(
                entity,
                it -> {
                    String name = it.tableName();
                    return StringOperation.isTrimEmptyOrNull(name) ? entityType.getSimpleName() : name;
                },
                daoClass::getSimpleName);
    }

    public static Class<?> getEntityType(Class<?> daoClass) {
        Class<?>[] genericTypes = getGenericType(daoClass);
        return genericTypes[0];
    }

    public static com.github.andyshao.mybatis.core.model.Entity getEntity(Class<?> daoClass) {
        return AnnotationEntityAnalysis.analysis(getEntityType(daoClass));
    }

    public static Class<?> parseMethodClass(String sql) {
        return GenericScriptSqlProvider.class;
    }
}
