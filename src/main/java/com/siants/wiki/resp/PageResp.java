package com.siants.wiki.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResp<T> {

    private long total;

    private List<T> list;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageResp{");
        sb.append("total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}