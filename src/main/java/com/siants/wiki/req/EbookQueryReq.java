package com.siants.wiki.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EbookQueryReq extends PageReq{
    private Long id;

    private String name;

    private Long categoryId2;

    @Override
    public String toString() {
        return "EbookQueryReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId2=" + categoryId2 +
                "} " + super.toString();
    }
}