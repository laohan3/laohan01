package com.laohan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.User;
import org.springframework.web.bind.annotation.ResponseBody;


public interface UserService extends IService<User> {
    ResponseResult userInfo();
    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
