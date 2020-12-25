package com.wah.persistence.mybatis.helper.page;

public class SimplePageRequest extends PageRequest{

    public SimplePageRequest(){
        super();
    }

    public SimplePageRequest(Long pageNumber, Long pageSize){
        super(pageNumber, pageSize);
    }

    @Override
    public Long getOffset(){
        return (getPageNumber() - 1) * getPageSize();
    }
}
