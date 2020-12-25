package com.wah.persistence.mybatis.helper.page;

public class MysqlPageRequest extends PageRequest{

    public MysqlPageRequest(){
        super();
    }

    public MysqlPageRequest(Long pageNumber, Long pageSize){
        super(pageNumber, pageSize);
    }

    @Override
    public Long getOffset(){
        return (getPageNumber() - 1) * getPageSize();
    }
}
