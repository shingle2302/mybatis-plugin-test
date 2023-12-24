package com.shingle.mybatis.config;

import com.shingle.mybatis.command.PermissionQuery;
import com.shingle.mybatis.enums.SqlCondition;
import com.shingle.mybatis.mapper.PermissionRuleMapper;
import com.shingle.mybatis.model.PermissionRule;
import com.shingle.mybatis.utils.SpringHelper;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
,@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class CustomInterceptor implements Interceptor {

    public CustomInterceptor() {
    }

    public static final DefaultObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final DefaultObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final DefaultReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            Object parameterObject = statementHandler.getParameterHandler().getParameterObject();
            if (parameterObject instanceof PermissionQuery) {
                PermissionQuery permissionQuery = (PermissionQuery) parameterObject;
                String permissionCode = permissionQuery.getPermissionCode();
                if(StringUtils.isEmpty(permissionCode)){
                    return invocation.proceed();
                }
                PermissionRuleMapper permissionRuleMapper = SpringHelper.getBean(PermissionRuleMapper.class);
                List<PermissionRule> permissionRules = permissionRuleMapper.queryByPermissionCode(permissionCode);
                if (CollectionUtils.isNotEmpty(permissionRules)) {
                    String sql = statementHandler.getBoundSql().getSql();
                    Statement statement = CCJSqlParserUtil.parse(sql);
                    Select select = (Select) statement;
                    PlainSelect plainSelect = select.getPlainSelect();
                    System.out.println("修改前: " + plainSelect);

                    Map<Integer, List<PermissionRule>> groupPermissionRuleMap = permissionRules.stream().collect(Collectors.groupingBy(PermissionRule::getGroupNo));
                    groupPermissionRuleMap.forEach((k, groupPermissionRules) -> {
                        for (PermissionRule permissionRule : groupPermissionRules) {
                            String permissionDimension = permissionRule.getPermissionDimension();
                            SqlCondition permissionCondition = SqlCondition.getConditionByCode(permissionRule.getPermissionCondition());
                            String permissionLogic = permissionRule.getPermissionLogic();
                            Expression where = plainSelect.getWhere();
                            Expression expression = null;
                            switch (permissionCondition) {
                                case GE:
                                    GreaterThanEquals greaterThanEquals = new GreaterThanEquals();
                                    greaterThanEquals.setLeftExpression(new Column(permissionDimension));
                                    greaterThanEquals.setRightExpression(new StringValue("user6"));
                                    expression = greaterThanEquals;
                                    break;
                                case LE:
                                    MinorThanEquals minorThanEquals = new MinorThanEquals();
                                    minorThanEquals.setLeftExpression(new Column(permissionDimension));
                                    minorThanEquals.setRightExpression(new StringValue("user6"));
                                    expression = minorThanEquals;
                                    break;
                                case IS_NULL:
                                    IsNullExpression isNullExpression = new IsNullExpression();
                                    isNullExpression.setLeftExpression(new Column(permissionDimension));
                                    expression = isNullExpression;
                                    break;
                                case GT:
                                    GreaterThan greaterThan = new GreaterThan();
                                    greaterThan.setLeftExpression(new Column(permissionDimension));
                                    greaterThan.setRightExpression(new StringValue("user6"));
                                    expression = greaterThan;
                                    break;
                                case LT:
                                    MinorThan minorThan = new MinorThan();
                                    minorThan.setLeftExpression(new Column(permissionDimension));
                                    minorThan.setRightExpression(new StringValue("user6"));
                                    expression = minorThan;
                                    break;
                                case LIKE:
                                    LikeExpression likeExpression = new LikeExpression();
                                    likeExpression.setLeftExpression(new Column(permissionDimension));
                                    likeExpression.setRightExpression(new StringValue("user6"));
                                    expression = likeExpression;
                                    break;
                                case NOT_LIKE:
                                    LikeExpression notLikeExpression = new LikeExpression();
                                    notLikeExpression.setNot(true);
                                    notLikeExpression.setLeftExpression(new Column(permissionDimension));
                                    notLikeExpression.setRightExpression(new StringValue("user6"));
                                    expression = notLikeExpression;
                                    break;
                                case IN:
                                    InExpression inExpression = new InExpression();
                                    inExpression.setLeftExpression(new Column(permissionDimension));
                                    ExpressionList itemsList =new ExpressionList(new StringValue("user@user"));
                                    inExpression.setRightExpression(itemsList);
                                    expression = inExpression;
                                    break;
                                case NOT_IN:
                                    InExpression notInExpression = new InExpression();
                                    notInExpression.setLeftExpression(new Column(permissionDimension));
                                    ExpressionList notItemList =new ExpressionList(new StringValue("user6"));
                                    notInExpression.setRightExpression(notItemList);
                                    notInExpression.setNot(true);
                                    expression = notInExpression;
                                    break;
                                case NE:
                                    NotEqualsTo notEqualsTo = new NotEqualsTo(new Column(permissionDimension), new StringValue("user6"));
                                    expression = notEqualsTo;
                                    break;
                                default:
                                    EqualsTo equalsTo = new EqualsTo(new Column(permissionDimension), new StringValue("user6"));
                                    expression = equalsTo;
                            }
                           if (Objects.nonNull(expression)){
                               if (null != where) {
                                   AndExpression andExpression = new AndExpression(where, expression);
                                   plainSelect.setWhere(andExpression);
                               } else {
                                   plainSelect.setWhere(expression);
                               }
                           }
                        }
                    });
                    System.out.println("修改后: " + plainSelect);
                    MetaObject metaObject = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
                    metaObject.setValue("delegate.boundSql.sql", plainSelect.toString());
                }
            }


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
