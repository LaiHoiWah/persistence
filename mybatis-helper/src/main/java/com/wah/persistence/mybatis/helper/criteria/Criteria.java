package com.wah.persistence.mybatis.helper.criteria;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public class Criteria{

    private List<Criterion> andCriterion = Lists.newArrayList();
    private List<Criterion> orCriterion  = Lists.newArrayList();
    private List<Criterion> orderBy      = Lists.newArrayList();
    private Criterion       limit        = null;

    public Criteria and(Criterion criterion){
        andCriterion.add(criterion);

        return this;
    }

    public Criteria or(Criterion criterion){
        orCriterion.add(criterion);

        return this;
    }

    public Criteria orderBy(Criterion criterion){
        orderBy.add(criterion);

        return this;
    }

    public Criteria limit(Criterion criterion){
        limit = criterion;

        return this;
    }

    public void clear(){
        andCriterion.clear();
        orCriterion.clear();
        orderBy.clear();

        limit = null;
    }

    public void clearCondition(){
        andCriterion.clear();
        orCriterion.clear();
    }

    public void clearOrderBy(){
        orderBy.clear();
    }

    public void clearLimit(){
        limit = null;
    }
}
