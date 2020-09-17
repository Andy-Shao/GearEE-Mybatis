package com.github.andyshao.mybatis.core.mapping.impl;

import com.github.andyshao.mybatis.core.mapping.ResultMappingFactory;
import com.github.andyshao.mybatis.core.model.AnnotationEntityAnalysis;
import com.github.andyshao.mybatis.core.model.Entity;
import com.github.andyshao.reflect.ClassOperation;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class AnnotationResultMappingFactory implements ResultMappingFactory {
    @Override
    public List<ResultMapping> buildResultMapping(Class<?> resultType, Configuration configuration) {
        if(ClassOperation.isPrimitiveType(resultType) || ClassOperation.isPrimitiveObject(resultType)) {
            return Collections.emptyList();
        } else if(ResultMappings.isDomainClass(resultType)) {
            final Entity entity = AnnotationEntityAnalysis.analysis(resultType);
            return ResultMappings.buildDomainClassResultMapping(resultType, configuration, entity);
        } else if(List.class.isAssignableFrom(resultType)) {
            return ResultMappings.buildListResultMapping(resultType, configuration);
        }
        throw new UnsupportedOperationException();
    }

}
