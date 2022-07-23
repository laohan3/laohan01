package com.laohan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.Link;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
