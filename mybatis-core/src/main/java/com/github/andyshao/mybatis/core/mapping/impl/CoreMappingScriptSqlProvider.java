package com.github.andyshao.mybatis.core.mapping.impl;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class CoreMappingScriptSqlProvider {
    private CoreMappingScriptSqlProvider(){}
    public static String countAll(Class<?> daoClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("SELECT count(*) FROM ").append(Mappers.getEntityName(daoClass));
        sb.append("</script>");
        return sb.toString();
    }

    public static String findAll(Class<?> daoClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("SELECT * FROM ").append(Mappers.getEntityName(daoClass));
        sb.append("</script>");
        return sb.toString();
    }
}
