package com.wah.persistence.mybatis.helper.factory;

import com.wah.persistence.mybatis.helper.factory.consts.Dialect;
import com.wah.persistence.mybatis.helper.page.MysqlPageRequest;
import com.wah.persistence.mybatis.helper.page.OraclePageRequest;
import com.wah.persistence.mybatis.helper.page.PageRequest;
import com.wah.persistence.mybatis.helper.page.SimplePageRequest;

public class PageRequestFactory{

    private String dialect;

    public PageRequestFactory(String dialect){
        this.dialect = dialect;
    }

    public PageRequest create(Long pageNumber, Long pageSize){
        switch(getDialect()){
            case MYSQL:
                return new MysqlPageRequest(pageNumber, pageSize);
            case ORACLE:
                return new OraclePageRequest(pageNumber, pageSize);
            case OTHER:
            default:
                return new SimplePageRequest(pageNumber, pageSize);
        }
    }

    private Dialect getDialect(){
        return Dialect.getByName(this.dialect);
    }
}
