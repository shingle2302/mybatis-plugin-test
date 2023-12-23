package com.shingle.mybatis.config;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.util.ReflectionUtils;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author shing2302
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class CustomInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            System.out.println("statementHandler:" + statementHandler);
            System.out.println("statementHandler.parameterHandler:" + statementHandler.getParameterHandler());
            System.out.println("statementHandler.parameterHandler.parameterObject:" + statementHandler.getParameterHandler().getParameterObject());
            String sql = statementHandler.getBoundSql().getSql();
            System.out.println("statementHandler.boundSql:" + sql);
            Statement statement = CCJSqlParserUtil.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect) selectBody;
            Expression where = plainSelect.getWhere();
            System.out.println("修改前: " + where);
            BinaryExpression binaryExpression = new EqualsTo(new Column("password"), new StringValue("user6"));
            if (null != where) {
                AndExpression andExpression = new AndExpression(where, binaryExpression);
                plainSelect.setWhere(andExpression);
            } else {
                plainSelect.setWhere(binaryExpression);
            }
            System.out.println("修改后: " + plainSelect.getWhere().toString());
            ReflectionUtils.doWithFields(statementHandler.getClass(), field -> {
                field.setAccessible(true);
                System.out.println("field:" + field);
                System.out.println("field.name:" + field.getName());
                System.out.println("field.type:" + field.getType());
                System.out.println("field.get(statementHandler):" + field.get(statementHandler));

            });
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //NOP
    }
}
