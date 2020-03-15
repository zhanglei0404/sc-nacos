package com.example.scuaa.service;

import com.alibaba.fastjson.JSON;
import com.example.scuaa.dao.UserDao;
import com.example.scuaa.model.PermissionDto;
import com.example.scuaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class ScUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userDao.loadUserByUsername(username);
        if (userDto!=null) {
            String principal = JSON.toJSONString(userDto);
            List<String> permissionList = findPermissionByUserId(userDto.getId());
            String[] permissionArray = new String[permissionList.size()];
            permissionList.toArray(permissionArray);
            return User.withUsername(principal).password(userDto.getPassword()).authorities(permissionArray).build();
        } else {
            // 如果没查到用户，返回null即可，由provider统一抛异常
            return null;
        }
    }

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    public List<String> findPermissionByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        List<PermissionDto> permissionDtoList = userDao.findPermissionByUserId(userId);
        List<String> permissions = new ArrayList<>();
        permissionDtoList.forEach(p -> permissions.add(p.getCode()));
        return permissions;
    }
}
