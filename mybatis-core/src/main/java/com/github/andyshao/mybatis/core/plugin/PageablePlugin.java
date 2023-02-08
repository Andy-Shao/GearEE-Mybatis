package com.github.andyshao.mybatis.core.plugin;

import com.github.andyshao.mybatis.core.dto.Pageable;
import com.github.andyshao.mybatis.core.dto.UnPaged;
import com.github.andyshao.mybatis.core.mapping.impl.Mappers;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright(c) 2023/2/6
 * Encoding: UNIX UTF-8
 *
 * @author Andy.Shao
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args={Connection.class, Integer.class})
})
public class PageablePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        // 配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        if(id.endsWith("page") || id.endsWith("Page")) {
            final BoundSql boundSql = statementHandler.getBoundSql();
            final Map parameterObject = (Map) boundSql.getParameterObject();
            final Pageable pageable = (Pageable) parameterObject.get(Mappers.DEFAULT_PAGE_NAME);
            String originSql = boundSql.getSql();
            if(!(pageable instanceof UnPaged)) {
                String limitSql = originSql + " LIMIT " + pageable.getOffset() + ", " + pageable.getPageSize();
                metaObject.setValue("delegate.boundSql.sql", limitSql);
            }
            if(pageable.isCountTotalSize()) {
                String countSql = "SELECT count(*) FROM (" + originSql + ") a";
                Connection connection = (Connection) invocation.getArgs()[0];
                final PreparedStatement countStatement = connection.prepareStatement(countSql);
                ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
                parameterHandler.setParameters(countStatement);
                ResultSet rs = countStatement.executeQuery();
                int totalSize = 0;
                if(rs.next()) totalSize = rs.getInt(1);
                pageable.setTotalSize(totalSize);
            }
        }
        return invocation.proceed();
    }
}
