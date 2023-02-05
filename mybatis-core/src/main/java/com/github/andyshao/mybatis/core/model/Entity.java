package com.github.andyshao.mybatis.core.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

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
public class Entity {
    private String tableName;
    private Class<?> definition;
    private List<Property> properties;
    private Property primaryKey;
}
