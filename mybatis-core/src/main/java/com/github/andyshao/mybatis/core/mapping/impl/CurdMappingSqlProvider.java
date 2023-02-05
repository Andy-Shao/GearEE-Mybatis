package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.model.Entity;
import com.github.andyshao.mybatis.core.model.Property;

import java.util.List;
import java.util.Objects;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/18
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class CurdMappingSqlProvider {
    private CurdMappingSqlProvider(){}
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
        sb.append("INSERT INTO ").append(entity.getTableName()).append(" (");
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
        sb.append("INSERT INTO ").append(entity.getTableName()).append(" (");
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
        sb.append("INSERT INTO ").append(entity.getTableName()).append(" (");
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
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        sb.append("<script>");
        sb.append("DELETE FROM ").append(entity.getTableName());
        sb.append(" WHERE ")
                .append(entity.getPrimaryKey().getColumn())
                .append("In ");
        sb.append("<foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">");
        sb.append("#{item}");
        sb.append("</foreach>");
        sb.append("</script>");
        return sb.toString();
    }

    public static String updateByPrimaryKey(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        final Property primaryKey = entity.getPrimaryKey();
        sb.append("<script>");
        sb.append("UPDATE ").append(entity.getTableName());
        sb.append(" <set>");
        for(Property property : entity.getProperties()) {
            if(Objects.equals(property.getDefinition(), primaryKey.getDefinition())) continue;
            sb.append(property.getColumn())
                    .append(" = #{")
                    .append(Mappers.DEFAULT_ENTITY_NAME)
                    .append(".")
                    .append(property.getName())
                    .append("},");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("</set>");
        sb.append(" WHERE ")
                .append(primaryKey.getColumn())
                .append(" = #{")
                .append(Mappers.DEFAULT_ENTITY_NAME)
                .append(".")
                .append(primaryKey.getName())
                .append("}");
        sb.append("</script>");
        return sb.toString();
    }

    public static String updateByPrimaryKeySelective(Class<?> daoClass) {
        final StringBuilder sb = new StringBuilder();
        final Entity entity = Mappers.getEntity(daoClass);
        final Property primaryKey = entity.getPrimaryKey();
        final List<Property> properties = entity.getProperties();
        sb.append("<script>");
        sb.append("UPDATE ").append(entity.getTableName());
        sb.append(" <set>");
        for(int i = 0; i< properties.size(); i++) {
            final Property property = properties.get(i);
            if(Objects.equals(property.getDefinition(), primaryKey.getDefinition())) continue;
            sb.append("<if test=\"")
                    .append(Mappers.DEFAULT_ENTITY_NAME)
                    .append(".")
                    .append(property.getName())
                    .append(" != null\">");
            sb.append(property.getColumn())
                    .append(" = #{")
                    .append(Mappers.DEFAULT_ENTITY_NAME)
                    .append(".")
                    .append(property.getName())
                    .append("}");
            if(i != properties.size() - 1) sb.append(",");
            sb.append("</if>");
        }
        sb.append("</set>");
        sb.append(" WHERE ")
                .append(primaryKey.getColumn())
                .append(" = #{")
                .append(Mappers.DEFAULT_ENTITY_NAME)
                .append(".")
                .append(primaryKey.getName())
                .append("}");
        sb.append("</script>");
        return sb.toString();
    }
}
