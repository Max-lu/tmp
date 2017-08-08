package cn.maxlu.demo.spring.mybatis;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(value = {
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DemoInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = null;

        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();

        if (invocation.getArgs().length > 1) {
            parameterObject = invocation.getArgs()[1];
        }
        String statementId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);

        String originalSql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        String realSql = getRealSql(originalSql, parameterMappings, parameterObject);

        System.out.println("耗时: " + (end - start) + "ms" + " - " + statementId + " - SQL: " + realSql);

        return result;
    }

    private String getRealSql(String originalSql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        String _sql = originalSql.replaceAll("\\s+", " ");
        if (parameterMappings == null || parameterMappings.isEmpty()) {
            return _sql;
        }
        Class<?> parameterClass = parameterObject.getClass();
        for (ParameterMapping parameterMapping : parameterMappings) {
            String property = parameterMapping.getProperty();
            String value;
            try {
                Field field = parameterClass.getDeclaredField(property);
                field.setAccessible(true);
                value = String.valueOf(field.get(parameterObject));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.err.println(e.getMessage());
                value = "null";
            }
            _sql = _sql.replaceFirst("\\?", value);
        }
        return _sql;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        properties.setProperty("key", "value");
    }

}
