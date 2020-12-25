package com.wah.persistence.mybatis.helper.factory;

import com.wah.persistence.mybatis.helper.factory.consts.Dialect;
import com.wah.persistence.mybatis.helper.page.*;

import java.util.List;

public class PageFactory{

    private String dialect;

    public PageFactory(String dialect){
        this.dialect = dialect;
    }

    public Page create(List content, Long total, PageRequest pageRequest){
        switch(getDialect()){
            case MYSQL:
                return new MysqlPage(content, total, pageRequest);
            case ORACLE:
                return new OraclePage(content, total, pageRequest);
            case OTHER:
            default:
                return new SimplePage(content, total, pageRequest);
        }
    }

    private Dialect getDialect(){
        return Dialect.getByName(this.dialect);
    }
}
