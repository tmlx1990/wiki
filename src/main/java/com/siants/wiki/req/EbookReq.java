package com.siants.wiki.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EbookReq extends PageReq{
    private Long id;

    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}