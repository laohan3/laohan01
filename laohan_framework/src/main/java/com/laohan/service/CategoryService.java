package com.laohan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laohan.domain.ResponseResult;

public interface CategoryService extends IService<com.laohan.domain.entity.Category> {
    ResponseResult getCategoryList();
}
