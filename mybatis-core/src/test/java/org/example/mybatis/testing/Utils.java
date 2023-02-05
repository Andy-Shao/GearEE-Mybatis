package org.example.mybatis.testing;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2020/9/17
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
public final class Utils {
    private static final String MYBATIS_CONFIG_FILE = "org/example/mybatis/config/mybatis-config.xml";

    public static final SqlSessionFactory buildSqlSessionFactory() throws IOException {
        InputStream configInputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_FILE);
        return new SqlSessionFactoryBuilder().build(configInputStream);
    }
}
