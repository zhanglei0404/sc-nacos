package com.example.scuaa.dao;

import com.example.scuaa.model.PermissionDto;
import com.example.scuaa.model.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    // 根据账号查询用户信息
    UserDto loadUserByUsername(String username);

    List<PermissionDto> findPermissionByUserId(String userId);
}
