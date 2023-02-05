package com.github.andyshao.mybatis.core;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public class MybatisException extends RuntimeException {
    public MybatisException() {}

    public MybatisException(String message) {
        super(message);
    }

    public MybatisException(Throwable ex) {
        super(ex);
    }

    public MybatisException(String message, Throwable ex) {
        super(message, ex);
    }
}
