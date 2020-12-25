package com.wah.persistence.mybatis.helper.interceptor.page;

import com.wah.commons.utils.StringUtils;
import com.wah.persistence.mybatis.helper.criteria.Criteria;
import com.wah.persistence.mybatis.helper.criteria.Criterion;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Properties;

@Intercepts(
    @Signature(
        type   = Executor.class,
        method = "query",
        args   = {
            MappedStatement.class,
            Object.class,
            RowBounds.class,
            ResultHandler.class
        }
    )
)
public class OraclePageInterceptor implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable{
        Object[]        args  = invocation.getArgs();
        MappedStatement ms    = (MappedStatement) args[0];
        String          sqlId = ms.getId();

        if(StringUtils.isNotBlank(sqlId) && sqlId.startsWith("find")){
            Object parameterObject = args[1];

            if(parameterObject instanceof Criteria){
                Criteria criteria = (Criteria) parameterObject;

                if(criteria.getLimit() != null){
                    Criterion criterion = criteria.getLimit();
                    BoundSql  boundSql  = ms.getBoundSql(parameterObject);
                    String    sql       = boundSql.getSql();

                    //分页SQL
                    String pageSql = MessageFormat.format("select * from ( select rownum as seq, a.* from ({0}) a ) where seq >= {1, number, #} and seq <= {2, number, #}", sql, criterion.getValue(), criterion.getSecondValue());
                    //创建新的SQLSource
                    SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), pageSql, boundSql.getParameterMappings());
                    //反射修改属性
                    Field field = ms.getClass().getDeclaredField("sqlSource");
                    field.setAccessible(true);
                    field.set(ms, sqlSource);
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target){
        if(target instanceof Executor){
            return Plugin.wrap(target, this);
        }

        return target;
    }

    @Override
    public void setProperties(Properties properties){

    }
}
