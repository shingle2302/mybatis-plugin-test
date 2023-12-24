package com.shingle.mybatis;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.Assert;
import org.junit.Test;

public class SqlParseUtil {


    @Test
    public void inExpressionTest() throws JSQLParserException {
        String sql = "select * from t_user where name in ('zhangSan')";
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select select = (Select) statement;
        PlainSelect plainSelect = select.getPlainSelect();
        Expression where = plainSelect.getWhere();

        InExpression inExpression = new InExpression();
        inExpression.setLeftExpression(new Column("id"));
        ExpressionList<StringValue> itemsList =new ExpressionList<>();
        itemsList.add(new StringValue("1"));
        itemsList.add(new StringValue("2"));
        itemsList.add(new StringValue("3"));
        inExpression.withRightExpression(itemsList);
        if (null != where) {
            AndExpression andExpression = new AndExpression(where, inExpression);
            plainSelect.setWhere(andExpression);
        } else {
            plainSelect.setWhere(inExpression);
        }
        System.out.println(plainSelect);
        Assert.assertEquals("SELECT * FROM t_user WHERE name IN ('zhangSan') AND id IN ('1', '2', '3')",plainSelect.toString());
    }
}
