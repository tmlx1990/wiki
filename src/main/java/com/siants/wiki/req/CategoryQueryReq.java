package com.siants.wiki.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryQueryReq extends PageReq{
    @Override
    public String toString() {
        return "CategoryQueryReq{} " + super.toString();
    }
}