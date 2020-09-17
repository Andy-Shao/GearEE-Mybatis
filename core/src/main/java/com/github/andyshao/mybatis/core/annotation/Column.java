package com.github.andyshao.mybatis.core.annotation;

import org.apache.ibatis.type.JdbcType;

import java.lang.annotation.*;

/**
 * 
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 20, 2019<br>
 * Encoding: UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value() default "";
    JdbcType jdbcType() default JdbcType.VARCHAR;
    boolean isIgnore() default false;
}
