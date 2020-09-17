package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.model.Entity;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class ResultMappings {
    public static List<ResultMapping> buildListResultMapping(Class<?> resultType, Configuration configuration) {
        // TODO
        return null;
    }

    public static List<ResultMapping> buildDomainClassResultMapping(
            Class<?> resultType, Configuration configuration, Entity entity) {
        // TODO
        return null;
    }

    public static boolean isDomainClass(Class<?> resultType) {
        // TODO
        return false;
    }
}
