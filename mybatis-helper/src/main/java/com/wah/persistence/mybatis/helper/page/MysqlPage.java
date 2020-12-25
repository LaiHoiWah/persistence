package com.wah.persistence.mybatis.helper.page;

import java.util.List;

public class MysqlPage<T> extends Page{

    public MysqlPage(List<T> content, Long total, PageRequest pageRequest){
        super(content, total, pageRequest);
    }

    @Override
    protected boolean hasNext(){
        return getPageRequest().getOffset() + getContent().size() < getTotal();
    }
}
