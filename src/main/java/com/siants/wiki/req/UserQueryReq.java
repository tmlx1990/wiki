package com.siants.wiki.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryReq extends PageReq{

    private String loginName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserQueryReq{");
        sb.append("loginName='").append(loginName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}