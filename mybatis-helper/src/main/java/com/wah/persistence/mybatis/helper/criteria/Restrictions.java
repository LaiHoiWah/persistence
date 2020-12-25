package com.wah.persistence.mybatis.helper.criteria;

import com.wah.persistence.mybatis.helper.page.PageRequest;

public class Restrictions{

    public static Criterion where(String property){
        Criterion criterion = new Criterion(property);

        return criterion;
    }

    public static Criterion where(){
        Criterion criterion = new Criterion();

        return criterion;
    }

    public static Criterion and(Criterion... values){
        Criterion criterion = new Criterion();
        criterion.and(values);

        return criterion;
    }

    public static Criterion or(Criterion... values){
        Criterion criterion = new Criterion();
        criterion.or(values);

        return criterion;
    }

    public static Criterion asc(String property){
        Criterion criterion = new Criterion();

        return criterion.asc(property);
    }

    public static Criterion desc(String property){
        Criterion criterion = new Criterion();

        return criterion.desc(property);
    }

    public static Criterion limit(PageRequest pageRequest){
        Criterion criterion = new Criterion();

        return criterion.limit(pageRequest);
    }
}
