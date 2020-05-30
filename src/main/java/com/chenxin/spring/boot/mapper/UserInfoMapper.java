package com.chenxin.spring.boot.mapper;

import com.chenxin.spring.boot.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

}