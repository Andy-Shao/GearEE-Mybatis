package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.model.Entity;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/5
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Deprecated
public final class ConditionalMappingSqlProvider {
    private ConditionalMappingSqlProvider(){}
    public static String findByConditional(Class<?> daoClass){
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        sb.append("<script>");
        sb.append("SELECT * FROM ").append(entity.getTableName());
        sb.append("</script>");
        return sb.toString();
    }

    public static String findByConditionalAndSort(Class<?> daoClass){
        return null;
    }

    public static String deleteByConditional(Class<?> daoClass){
        return null;
    }

    public static String updateByConditional(Class<?> daoClass) {
        return null;
    }
}
