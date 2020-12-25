package com.wah.persistence.mybatis.helper.page;

public class OraclePageRequest extends PageRequest{

    public OraclePageRequest(){
        super();
    }

    public OraclePageRequest(Long pageNumber, Long pageSize){
        super(pageNumber, pageSize);
    }

    @Override
    public Long getOffset(){
        return (getPageNumber() - 1) * getPageSize() + 1;
    }
}
