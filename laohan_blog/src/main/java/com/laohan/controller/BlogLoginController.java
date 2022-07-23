package com.laohan.controller;

import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.User;
import com.laohan.enums.AppHttpCodeEnum;
import com.laohan.exception.SystemException;
import com.laohan.service.BlogLoginService;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Validated
@RestController
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;


    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())||!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        return blogLoginService.login(user);
    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }


}











