package com.springboot.login_api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.springboot.login_api.dto.SnsLoginDto;

@Mapper
@Repository
public interface SnsLoginDao {

    int findBySnsId(SnsLoginDto dto);

    void insertSnsId(SnsLoginDto dto);

}
