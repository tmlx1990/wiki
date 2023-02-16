package com.siants.wiki.mapper;

import com.siants.wiki.domain.User;
import com.siants.wiki.domain.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User row);

    int insertSelective(User row);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);
}