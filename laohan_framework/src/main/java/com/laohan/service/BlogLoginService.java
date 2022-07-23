package com.laohan.service;

import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
