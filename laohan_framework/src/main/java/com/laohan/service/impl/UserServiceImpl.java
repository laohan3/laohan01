package com.laohan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.User;
import com.laohan.domain.vo.UserInfoVo;
import com.laohan.enums.AppHttpCodeEnum;
import com.laohan.exception.SystemException;
import com.laohan.mapper.UserMapper;
import com.laohan.service.UserService;
import com.laohan.utils.BeanCopyUtils;
import com.laohan.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService  {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserMapper userMapper;
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = userMapper.selectById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);

    }
    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }
    //用户注册
    @Override
    public ResponseResult register(User user) {
        //判断用户名,昵称等是否为空
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        //判断用户名,昵称等是否重复(从数据库查询)
        if (userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if (emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //给密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();

    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(User::getEmail,email);
        int emailCount = count(queryWrapper);
        return emailCount>0;
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(User::getNickName,nickName);
        int nickNameCount = count(queryWrapper);
        return nickNameCount>0;
    }

    private boolean userNameExist(String userName) {
        //查询数据库,判断用户名是否已存在
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserName,userName);
        int userNameCount = count(queryWrapper);
        return userNameCount>0;
    }


}
