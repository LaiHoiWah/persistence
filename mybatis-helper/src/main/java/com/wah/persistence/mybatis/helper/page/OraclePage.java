package com.wah.persistence.mybatis.helper.page;

import java.util.List;

public class OraclePage<T> extends Page{

    public OraclePage(List<T> content, Long total, PageRequest pageRequest){
        super(content, total, pageRequest);
    }

    @Override
    protected boolean hasNext(){
        return getPageRequest().getOffset() + getContent().size() - 1 < getTotal();
    }
}
