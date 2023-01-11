package com.siants.wiki.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocQueryReq extends PageReq{
    @Override
    public String toString() {
        return "DocQueryReq{} " + super.toString();
    }
}