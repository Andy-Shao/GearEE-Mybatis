package com.github.andyshao.mybatis.core.mapping.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.github.andyshao.mybatis.core.mapping.ConditionalMapping;
import com.github.andyshao.mybatis.core.mapping.CoreMapping;
import com.github.andyshao.mybatis.core.mapping.CurdMapping;
import com.github.andyshao.mybatis.core.mapping.PageMapping;
import com.github.andyshao.reflect.ClassOperation;

import lombok.NonNull;

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
	public static final String DEFAULT_PK_NAME = "pk";
	public static final String DEFAULT_ENTITY_NAME = "entity";
	public static final String DEFAULT_CONDITIONAL_NAME = "cond";
	public static final String DEFAULT_SETS_NAME = "sets";
	public static final String DEFAULT_SORT_NAME = "sort";
	public static final String DEFAULT_PAGE_NAME = "pageable";
	@SuppressWarnings("rawtypes")
	public static final Class[] GENERIC_DAO_CLASS = new Class[] {
            CoreMapping.class, CurdMapping.class, ConditionalMapping.class, PageMapping.class
    };
	
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
}
