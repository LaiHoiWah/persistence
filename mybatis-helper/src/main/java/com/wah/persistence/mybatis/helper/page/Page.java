package com.wah.persistence.mybatis.helper.page;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Page<T>{

    private Long        total;
    private PageRequest pageRequest;
    private List<T>     content;
    private Boolean     hasNext;

    public Page(List<T> content, Long total, PageRequest pageRequest){
        this.content     = content == null ? Lists.newArrayList() : content;
        this.total       = (total == null || total < 0) ? 0 : total;
        this.pageRequest = pageRequest;
        this.hasNext     = hasNext();
    }

    protected abstract boolean hasNext();
}
