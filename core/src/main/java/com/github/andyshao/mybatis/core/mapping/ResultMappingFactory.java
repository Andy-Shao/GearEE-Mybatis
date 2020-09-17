package com.github.andyshao.mybatis.core.mapping;

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
public interface ResultMappingFactory {
    List<ResultMapping> buildResultMapping(Class<?> ResultType, Configuration configuration);
}
