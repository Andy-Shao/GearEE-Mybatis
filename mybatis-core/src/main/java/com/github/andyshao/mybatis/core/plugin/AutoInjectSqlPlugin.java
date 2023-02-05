package com.github.andyshao.mybatis.core.plugin;

import com.github.andyshao.mybatis.core.mapping.impl.AnnotationResultMappingFactory;
import com.github.andyshao.mybatis.core.mapping.impl.GenericScriptSqlProvider;
import com.github.andyshao.mybatis.core.mapping.impl.Mappers;
import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshao.reflect.FieldOperation;
import com.github.andyshao.reflect.InvocationTargetException;
import com.github.andyshao.reflect.MethodOperation;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Intercepts({
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class})
})
public class AutoInjectSqlPlugin implements Interceptor {
	private final AnnotationResultMappingFactory resultMappingFactory = new AnnotationResultMappingFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameterObject = args[1];
        String sql = mappedStatement.getBoundSql(parameterObject).getSql();
        if(Objects.equals(sql.trim(), Mappers.GENERIC_DAO_QUERY)) {
            synchronized (this) {
                sql = mappedStatement.getBoundSql(parameterObject).getSql();
                if(Objects.equals(sql.trim(), Mappers.GENERIC_DAO_QUERY)) {
                    Configuration configuration = mappedStatement.getConfiguration();

                    provideResultMap(mappedStatement, configuration);
                    provideSqlSource(mappedStatement, parameterObject, configuration);
                }
            }
        }
        return invocation.proceed();
	}
	
	void provideSqlSource(MappedStatement mappedStatement, Object parameterObject, Configuration configuration)
            throws IllegalAccessException, InvocationTargetException {
        SqlSource sqlSource = new XMLLanguageDriver().createSqlSource(configuration,
                provideScript(mappedStatement),
                Objects.isNull(parameterObject) ? null : parameterObject.getClass());
        setField(mappedStatement,"sqlSource", sqlSource);
    }

    void provideResultMap(MappedStatement mappedStatement, Configuration configuration) {
        String resultMapId = parseResultMappingId(mappedStatement);
        Class<?> resultType = parseResultType(mappedStatement);
        List<ResultMapping> resultMappings = resultMappingFactory.buildResultMapping(resultType, configuration);
        ResultMap resultMap = new ResultMap.Builder(configuration, resultMapId, resultType, resultMappings)
                .build();
        List<ResultMap> resultMaps = Collections.singletonList(resultMap);
        setField(mappedStatement, "resultMaps", resultMaps);
    }

    static void setField(MappedStatement mappedStatement, String fieldName, Object fieldValue) {
        Field resultMapsField = FieldOperation.getDeclaredField(MappedStatement.class,
                fieldName);
        resultMapsField.setAccessible(true);
        FieldOperation.setFieldValue(mappedStatement, resultMapsField, fieldValue);
    }

    static final String provideScript(MappedStatement mappedStatement) throws IllegalAccessException,
            InvocationTargetException {
        Class<?> daoClass = parseDaoClass(mappedStatement);
        Method provider = MethodOperation.getDeclaredMethod(GenericScriptSqlProvider.class,
                parseMapperMethod(mappedStatement), Class.class);
        return MethodOperation.invoked(null, provider, daoClass).toString().trim();
    }

    static final Class<?> parseResultType(MappedStatement mappedStatement) {
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        verifyResultMaps(resultMaps);

        Class<?> ret = resultMaps.get(0).getType();
        if(ret.isAssignableFrom(List.class)) {
            ret = Mappers.getGenericType(parseDaoClass(mappedStatement))[0];
        }
        return ret;
    }

    static final String parseResultMappingId(MappedStatement mappedStatement) {
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        verifyResultMaps(resultMaps);

        return resultMaps.get(0).getId();
    }

    static final void verifyResultMaps(List<ResultMap> resultMaps) {
        if(resultMaps.isEmpty()) {
            throw new UnsupportedOperationException("The resultMap doesn't exist");
        }
    }

    static final Class<?> parseDaoClass(MappedStatement mappedStatement) {
        String id = mappedStatement.getId();
        int index = id.lastIndexOf('.');
        return ClassOperation.forName(id.substring(0, index));
    }

    static final String parseMapperMethod(MappedStatement mappedStatement) {
        String methodName = mappedStatement.getId();
        int index = methodName.lastIndexOf('.');
        methodName = methodName.substring(index + 1);
        return methodName;
    }

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {}
}
