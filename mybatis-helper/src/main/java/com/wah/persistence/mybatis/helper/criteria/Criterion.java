package com.wah.persistence.mybatis.helper.criteria;

import com.wah.commons.utils.AssertUtils;
import com.wah.commons.utils.SpellingUtils;
import com.wah.persistence.mybatis.helper.page.PageRequest;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

public class Criterion{

    //运算符
    private final static String LIKE            = "LIKE";
    private final static String NOT_LIKE        = "NOT LIKE";
    private final static String IS_NULL         = "IS NULL";
    private final static String IS_NOT_NULL     = "IS NOT NULL";
    private final static String IN              = "IN";
    private final static String NOT_IN          = "NOT IN";
    private final static String EQUAL           = "=";
    private final static String NOT_EQUAL       = "<>";
    private final static String LESS_EQUAL      = "<=";
    private final static String GREATER_EQUAL   = ">=";
    private final static String LESS_THAN       = "<";
    private final static String GREATER_THAN    = ">";
    private final static String BETWEEN         = "BETWEEN";
    private final static String NOT_BETWEEN     = "NOT BETWEEN";
    private final static String DESC            = "DESC";
    private final static String ASC             = "ASC";
    private final static String OR              = "OR";
    private final static String AND             = "AND";
    //函数
    private final static String LIMIT           = "LIMIT";

    //属性名
    @Getter
    private String property;

    //运算符
    @Getter
    private String operator;

    //值
    @Getter
    private Object value;

    //第二值
    @Getter
    private Object secondValue;

    //无值标识
    @Getter
    private Boolean noValue           = false;

    //单值标识
    @Getter
    private Boolean singleValue       = false;

    //两值标识
    @Getter
    private Boolean twinsValue        = false;

    //多值标识
    @Getter
    private Boolean listValue         = false;

    //SQL值标识
    @Getter
    private Boolean sqlValue          = false;

    //Criterion值标识
    @Getter
    private Boolean criterionValue    = false;

    //Criterion与标识
    @Getter
    private Boolean andCriterionValue = false;

    //Criterion或标识
    @Getter
    private Boolean orCriterionValue  = false;

    public Criterion(){

    }

    public Criterion(String property){
        assertProperty(property);

        this.property = SpellingUtils.underline(property);
    }

    public Criterion eq(Object value){
        assertValue(value);

        this.value       = value;
        this.operator    = EQUAL;
        this.singleValue = true;

        return this;
    }

    public Criterion notEq(Object value){
        assertValue(value);

        this.value       = value;
        this.operator    = NOT_EQUAL;
        this.singleValue = true;

        return this;
    }

    public Criterion lt(Object value){
        assertValue(value);

        this.value       = value;
        this.operator    = LESS_THAN;
        this.singleValue = true;

        return this;
    }

    public Criterion le(Object value){
        assertValue(value);

        this.value       = value;
        this.operator    = LESS_EQUAL;
        this.singleValue = true;

        return this;
    }

    public Criterion gt(Object value){
        assertValue(value);

        this.value       = value;
        this.operator    = GREATER_THAN;
        this.singleValue = true;

        return this;
    }

    public Criterion ge(Object value){
        assertValue(value);

        this.value       = value;
        this.operator    = GREATER_EQUAL;
        this.singleValue = true;

        return this;
    }

    public Criterion like(Object value){
        assertValue(value);

        this.value       = "%" + value + "%";
        this.operator    = LIKE;
        this.singleValue = true;

        return this;
    }

    public Criterion leftLike(Object value){
        assertValue(value);

        this.value       = "%" + value;
        this.operator    = LIKE;
        this.singleValue = true;

        return this;
    }

    public Criterion rightLike(Object value){
        assertValue(value);

        this.value       = value + "%";
        this.operator    = LIKE;
        this.singleValue = true;

        return this;
    }

    public Criterion notLike(Object value){
        assertValue(value);

        this.value       = "%" + value + "%";
        this.operator    = NOT_LIKE;
        this.singleValue = true;

        return this;
    }

    public Criterion notLeftLike(Object value){
        assertValue(value);

        this.value       = "%" + value;
        this.operator    = NOT_LIKE;
        this.singleValue = true;

        return this;
    }

    public Criterion notRightLike(Object value){
        assertValue(value);

        this.value       = value + "%";
        this.operator    = NOT_LIKE;
        this.singleValue = true;

        return this;
    }

    public Criterion between(Object min, Object max){
        assertValue(min);
        assertValue(max);

        this.value       = min;
        this.secondValue = max;
        this.operator    = BETWEEN;
        this.twinsValue  = true;

        return this;
    }

    public Criterion notBetween(Object min, Object max){
        assertValue(min);
        assertValue(max);

        this.value       = min;
        this.secondValue = max;
        this.operator    = NOT_BETWEEN;
        this.twinsValue  = true;

        return this;
    }

    public Criterion in(List value){
        assertValue(value);

        this.value     = value;
        this.operator  = IN;
        this.listValue = true;

        return this;
    }

    public Criterion notIn(List value){
        assertValue(value);

        this.value     = value;
        this.operator  = NOT_IN;
        this.listValue = true;

        return this;
    }

    public Criterion isNull(){
        this.operator = IS_NULL;
        this.noValue  = true;

        return this;
    }

    public Criterion notNull(){
        this.operator = IS_NOT_NULL;
        this.noValue  = true;

        return this;
    }

    public Criterion and(Criterion... value){
        assertValue(value);

        this.value             = value;
        this.operator          = AND;
        this.andCriterionValue = true;

        return this;
    }

    public Criterion or(Criterion... value){
        assertValue(value);

        this.value            = value;
        this.operator         = OR;
        this.orCriterionValue = true;

        return this;
    }

    public Criterion desc(String property){
        assertProperty(property);

        this.property = SpellingUtils.underline(property);
        this.operator = DESC;
        this.noValue  = true;

        return this;
    }

    public Criterion asc(String property){
        assertProperty(property);

        this.property = SpellingUtils.underline(property);
        this.operator = ASC;
        this.noValue  = true;

        return this;
    }

    public Criterion limit(PageRequest pageRequest){
        assertValue(pageRequest);

        this.value       = pageRequest.getOffset();
        this.secondValue = pageRequest.getPageSize();
        this.operator    = LIMIT;
        this.twinsValue  = true;

        return this;
    }

    private void assertProperty(String property){
        AssertUtils.hasText(property, "列名不能为空");
    }

    private void assertValue(Object value){
        AssertUtils.notNull(value, "查询参数不能为空");

        if(value instanceof String){
            AssertUtils.hasText((String) value, "查询参数不能为空");

        }else if(value instanceof Collection){
            AssertUtils.notEmpty((Collection) value, "查询参数不能为空");

        }
    }
}
