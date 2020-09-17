package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.annotation.Entity;
import com.github.andyshao.util.ObjectOperation;

import java.util.Objects;

/**
 * 
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 21, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class GenericScriptSqlProvider {
    //TODO

    public static String countAll(Class<?> daoClass) {
        return CoreMappingScriptSqlProvider.countAll(daoClass);
    }

    public static String findAll(Class<?> daoClass) {
        return CoreMappingScriptSqlProvider.findAll(daoClass);
    }

}
