package com.github.andyshao.mybatis.core.model;

import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Field;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Getter
@Builder
public class Property {
    public static final String DEFAULT_JDBC_TYPE = "VARCHAR";
    private Field definition;
    private String column;
    private String name;
    private String jdbcType;
    private Boolean isPrimaryKey;
}
