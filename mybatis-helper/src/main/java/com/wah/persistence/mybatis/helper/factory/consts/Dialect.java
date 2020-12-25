package com.wah.persistence.mybatis.helper.factory.consts;

import com.wah.commons.utils.StringUtils;

public enum Dialect{

    OTHER,

    MYSQL,

    ORACLE,

    ;

    public static Dialect getByName(String name){
        if(StringUtils.isBlank(name)){
            return OTHER;
        }

        for(Dialect dialect : Dialect.values()){
            if(dialect.name().equalsIgnoreCase(name)){
                return dialect;
            }
        }

        return OTHER;
    }
}
