package com.laohan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.laohan.domain.entity.LoginUser;
import com.laohan.domain.entity.User;
import com.laohan.mapper.UserMapper;
import com.laohan.utils.WebUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查询
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,s);
        User user = userMapper.selectOne(queryWrapper);
        //输入用户名错误会抛出该异常返回给前端
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在!");
        }
        // TODO: 2022/5/27 查询权限信息封装
        return new LoginUser(user);
    }
}
