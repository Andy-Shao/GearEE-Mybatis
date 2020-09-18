package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.model.Entity;
import com.github.andyshao.mybatis.core.model.Property;

import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/18
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class CurdMappingSqlProvider {
    public static String findByPrimaryKey(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        sb.append("<script>");
        sb.append("SELECT * FROM ").append(entity.getTableName());
        sb.append(" WHERE ")
                .append(entity.getPrimaryKey().getColumn())
                .append(" = #{")
                .append(Mappers.DEFAULT_PK_NAME)
                .append("}");
        sb.append("</script>");
        return sb.toString();
    }

    public static String save(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        sb.append("<script>");
        sb.append("INSERT INTO (");
        for(Property property : entity.getProperties()) {
            sb.append(property.getColumn()).append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(") VALUES (");
        for(Property property : entity.getProperties()) {
            sb.append("#{").append(Mappers.DEFAULT_ENTITY_NAME).append(".").append(property.getName()).append("},");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(")");
        sb.append("</script>");
        return sb.toString();
    }

    public static String saveList(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        sb.append("<script>");
        sb.append("INSERT INTO (");
        for(Property property : entity.getProperties()) {
            sb.append(property.getColumn()).append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(") VALUES ");
        sb.append("<foreach item=\"item\" index=\"index\" collection=\"list\" separator=\",\">");
        sb.append("(");
        for(Property property : entity.getProperties()) {
            sb.append("#{item.").append(property.getName()).append("},");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(")");
        sb.append("</foreach>");
        sb.append("</script>");
        return sb.toString();
    }

    public static String saveSelective(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        final List<Property> properties = entity.getProperties();
        sb.append("<script>");
        sb.append("INSERT INTO (");
        for(int i = 0; i< properties.size(); i++) {
            final Property property = properties.get(i);
            sb.append("<if test=\"")
                    .append(Mappers.DEFAULT_ENTITY_NAME)
                    .append(".")
                    .append(property.getName())
                    .append("!= null\">");
            sb.append(property.getColumn());
            if(properties.size() - 1 != i) sb.append(",");
            sb.append("</if>");
        }
        sb.append(") VALUES (");
        for(int i = 0; i< properties.size(); i++) {
            final Property property = properties.get(i);
            sb.append("<if test=\"")
                    .append(Mappers.DEFAULT_ENTITY_NAME)
                    .append(".")
                    .append(property.getName())
                    .append("!= null\">");
            sb.append("#{")
                    .append(Mappers.DEFAULT_ENTITY_NAME)
                    .append(".")
                    .append(property.getName())
                    .append("}");
            if(properties.size() - 1 != i) sb.append(",");
            sb.append("</if>");
        }
        sb.append(")");
        sb.append("</script>");
        return sb.toString();
    }

    public static String deleteByPrimaryKey(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        sb.append("<script>");
        sb.append("DELETE FROM ").append(entity.getTableName());
        sb.append(" WHERE ")
                .append(entity.getPrimaryKey().getColumn())
                .append(" = #{")
                .append(Mappers.DEFAULT_PK_NAME)
                .append("}");
        sb.append("</script>");
        return sb.toString();
    }

    public static String deleteByPrimaryKeyList(Class<?> daoClass) {
        //TODO
        return null;
    }

    public static String updateByPrimaryKey(Class<?> daoClass) {
        //TODO
        return null;
    }

    public static String updateByPrimaryKeyList(Class<?> daoClass) {
        //TODO
        return null;
    }

    public static String updateByPrimaryKeySelective(Class<?> daoClass) {
        //TODO
        return null;
    }
}
