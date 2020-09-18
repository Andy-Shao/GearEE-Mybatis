package com.github.andyshao.mybatis.core.mapping.impl;

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

    public static String findByPrimaryKey(Class<?> daoClass) {
        return CurdMappingSqlProvider.findByPrimaryKey(daoClass);
    }

    public static String save(Class<?> daoClass) {
        return CurdMappingSqlProvider.save(daoClass);
    }

    public static String saveList(Class<?> daoClass) {
        return CurdMappingSqlProvider.saveList(daoClass);
    }

    public static String saveSelective(Class<?> daoClass) {
        return CurdMappingSqlProvider.saveSelective(daoClass);
    }

    public static String deleteByPrimaryKey(Class<?> daoClass) {
        return CurdMappingSqlProvider.deleteByPrimaryKey(daoClass);
    }

    public static String deleteByPrimaryKeyList(Class<?> daoClass) {
        return CurdMappingSqlProvider.deleteByPrimaryKeyList(daoClass);
    }

    public static String updateByPrimaryKey(Class<?> daoClass) {
        return CurdMappingSqlProvider.updateByPrimaryKey(daoClass);
    }

    public static String updateByPrimaryKeyList(Class<?> daoClass) {
        return CurdMappingSqlProvider.updateByPrimaryKeySelective(daoClass);
    }

    public static String updateByPrimaryKeySelective(Class<?> daoClass) {
        return CurdMappingSqlProvider.updateByPrimaryKeySelective(daoClass);
    }
}
