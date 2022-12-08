package com.siants.wiki.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageReq {

    private int page;

    private int size;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageReq{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}